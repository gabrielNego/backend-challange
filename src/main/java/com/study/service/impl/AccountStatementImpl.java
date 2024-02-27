package com.study.service.impl;

import java.time.LocalDateTime;
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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountStatementImpl implements CustomerStatement {
	
	@Inject
	CustomerRepository customerRepository;

	@Inject
	TransactionHistoryRepository transactionHistoryRepository;

	@Override
	public Statement statement(Long customerId) {
		Log.infof("Get statement from customer [%s]", customerId);
		Customer customer = this.findCustomer(customerId);
		return new Statement(new CustomerBalance(customer.getLimit(), customer.getBalance(), LocalDateTime.now()),
				transactionHistoryRepository.find("customerId", Sort.by("date", Direction.Descending), customerId)
						.page(Page.of(0,10))
						.list()
						.stream()
						.map(convertTransactionHistoryToTransaction)
						.toList());
	}
	
	private Customer findCustomer(Long customerId) {
		return customerRepository.findByIdOptional(customerId).orElseThrow(() -> new CustomerNotFoundException("Can not found the account"));
	}
	
	private Function<TransactionHistory, Transaction> convertTransactionHistoryToTransaction = transactionHistory -> {
		return Transaction
				.builder()
				.valor(transactionHistory.getValue())
				.tipo(OperationTypeEnum.valueOf(transactionHistory.getType()))
				.descricao(transactionHistory.getDescription())
				.realizada_em(transactionHistory.getDate())
				.build();
	};

}
