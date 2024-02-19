package com.study.service;

import com.study.model.CustomerBalance;
import com.study.model.Transaction;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CustomerMoviment {
	
	default CustomerBalance transaction(Long customerId, Transaction transaction) {
		switch (transaction.getTipo()) {
		case CREDIT:
			return credit(customerId, transaction);
		case DEBIT:
			return debit(customerId, transaction);
		default:
			throw new IllegalArgumentException("Invalid operation: " + transaction.getTipo());
		}
	}
	
	CustomerBalance credit(Long customerId, Transaction transaction);
	CustomerBalance debit(Long customerId, Transaction transaction);
}
