package com.study.service.impl;

import java.util.List;
import java.util.function.Function;

import com.study.exception.CustomerNotFoundException;
import com.study.model.CustomerBalance;
import com.study.model.Statement;
import com.study.model.Transaction;
import com.study.model.enums.OperationTypeEnum;
import com.study.repository.CustomerRepository;
import com.study.repository.TransactionHistoryRepository;
import com.study.repository.entity.Customer;
import com.study.repository.entity.TransactionHistory;
import com.study.service.CustomerStatement;

import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountStatementImpl implements CustomerStatement {
	
	@Inject
	CustomerRepository customerRepository;

	@Inject
	TransactionHistoryRepository transactionHistoryRepository;

	@Override
	public Uni<Statement> statement(Long customerId) {
		Log.infof("Get statement from customer [%s]", customerId);
		Uni<Customer> uniCustomer = findCustomer(customerId);
		Uni<List<TransactionHistory>> uniTransactionHistory = transactionHistoryRepository
				.find("customerId", Sort.by("date", Direction.Descending), customerId)
				.page(Page.of(0, 10))
				.list();
		return Uni
			.combine()
			.all()
			.unis(uniCustomer, uniTransactionHistory)
			.asTuple()
			.onItem()
			.ifNull()
			.failWith(new CustomerNotFoundException("Can not found the account"))
			.map(tuple -> {
				Customer customer = tuple.getItem1();
				List<TransactionHistory> transactionHistories = tuple.getItem2();
				return new Statement(
						new CustomerBalance(customer.getLimit(), customer.getBalance()),
						transactionHistories
						.stream()
						.map(convertTransactionHistoryToTransaction)
						.toList());
			});
	}
	
	private Uni<Customer> findCustomer(Long customerId) {
		return customerRepository.findById(customerId);
	}
	
	private Function<TransactionHistory, Transaction> convertTransactionHistoryToTransaction = transactionHistory ->
		Transaction
				.builder()
				.valor(transactionHistory.getValue())
				.tipo(OperationTypeEnum.getEnumFromType(transactionHistory.getType()))
				.descricao(transactionHistory.getDescription())
				.realizada_em(transactionHistory.getDate())
				.build();

}
