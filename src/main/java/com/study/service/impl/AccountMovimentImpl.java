package com.study.service.impl;

import java.time.LocalDateTime;

import com.study.exception.BalanceOutOfLimitException;
import com.study.exception.CustomerNotFoundException;
import com.study.model.CustomerBalance;
import com.study.model.Transaction;
import com.study.repository.CustomerRepository;
import com.study.repository.TransactionHistoryRepository;
import com.study.repository.entity.Customer;
import com.study.repository.entity.TransactionHistory;
import com.study.service.CustomerMoviment;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountMovimentImpl implements CustomerMoviment {
	
	@Inject
	private CustomerRepository customerRepository;

	@Inject
	private TransactionHistoryRepository transactionHistoryRepository;

	@Override
	public Uni<CustomerBalance> credit(Long customerId, Transaction transaction) {
		Log.infof("Credit Operation - value %s to %s" , transaction.getValor(), customerId);
		return findCustomer(customerId)
				.onItem()
				.ifNull()
				.failWith(new CustomerNotFoundException("Can not found the account"))
				.invoke(customer -> {
					customer.setBalance(customer.getBalance() + transaction.getValor());
				})
				.invoke(customer -> customerRepository.persist(customer))
				.invoke(customer -> transactionHistoryRepository.persist(buildTransactionHistory(transaction, customer.getId())))
				.map(customer -> new CustomerBalance(customer.getLimit(), customer.getBalance()));
	}

	@Override
	public Uni<CustomerBalance> debit(Long customerId, Transaction transaction) {
		Log.infof("Debit Operation - value %s to %s" , transaction.getValor(), customerId);
		return findCustomer(customerId)
			.onItem()
			.ifNull()
			.failWith(new CustomerNotFoundException("Can not found the account"))
			.invoke(customer -> {
				Long amount = Math.subtractExact(customer.getBalance(), transaction.getValor());
				if(amount < Math.negateExact(customer.getLimit())) {
					throw new BalanceOutOfLimitException("Balance can not be less than limit");
				}
				customer.setBalance(amount);
			})
			.invoke(customer -> customerRepository.persist(customer))
			.invoke(customer -> transactionHistoryRepository.persist(buildTransactionHistory(transaction, customer.getId())))
			.map(customer -> new CustomerBalance(customer.getLimit(), customer.getBalance()));
	}

	private Uni<Customer> findCustomer(Long customerId) {
		return customerRepository.findById(customerId);
	}
	
	private TransactionHistory buildTransactionHistory(Transaction transaction, Long customerId) {
		return TransactionHistory
				.builder()
				.date(LocalDateTime.now())
				.customerId(customerId)
				.type(transaction.getTipo().name())
				.value(transaction.getValor())
				.description(transaction.getDescricao())
				.build();
	}

}
