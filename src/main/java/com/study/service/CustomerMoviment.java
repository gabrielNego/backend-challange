package com.study.service;

import com.study.model.CustomerBalance;
import com.study.model.Transaction;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CustomerMoviment {
	
	default Uni<CustomerBalance> transaction(Long customerId, Transaction transaction) {
		switch (transaction.getTipo()) {
		case CREDIT:
			return credit(customerId, transaction);
		case DEBIT:
			return debit(customerId, transaction);
		default:
			throw new IllegalArgumentException("Invalid operation: " + transaction.getTipo());
		}
	}
	
	Uni<CustomerBalance> credit(Long customerId, Transaction transaction);
	Uni<CustomerBalance> debit(Long customerId, Transaction transaction);
}
