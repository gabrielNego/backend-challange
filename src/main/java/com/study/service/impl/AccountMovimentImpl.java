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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountMovimentImpl implements CustomerMoviment {
	
	@Inject
	private CustomerRepository customerRepository;

	@Inject
	private TransactionHistoryRepository transactionHistoryRepository;

	@Override
	public CustomerBalance credit(Long customerId, Transaction transaction) {
		Log.infof("Credit Operation - value %s to %s" , transaction.getValor(), customerId);
		
		Customer customer = findCustomer(customerId);
		customer.setBalance(customer.getBalance() + transaction.getValor());

		customerRepository.persist(customer);
		transactionHistoryRepository.persist(buildTransactionHistory(transaction, customerId));
		
		return new CustomerBalance(customer.getLimit(), customer.getBalance());
	}

	@Override
	public CustomerBalance debit(Long customerId, Transaction transaction) {
		Log.infof("Debit Operation - value %s to %s" , transaction.getValor(), customerId);
		
		Customer customer = findCustomer(customerId);
		Long amount = Math.subtractExact(customer.getBalance(), transaction.getValor());

		if(amount < Math.negateExact(customer.getLimit())) {
			throw new BalanceOutOfLimitException("Balance can not be less than limit");
		}
		
		customer.setBalance(amount);

		customerRepository.persist(customer);
		transactionHistoryRepository.persist(buildTransactionHistory(transaction, customerId));
		
		return new CustomerBalance(customer.getLimit(), customer.getBalance());
	}

	private Customer findCustomer(Long customerId) {
		return customerRepository.findByIdOptional(customerId).orElseThrow(() -> new CustomerNotFoundException("Can not found the account"));
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
