package com.study.service;

import com.study.model.AccountBalance;
import com.study.model.Transaction;

public interface AccountMoviment {
	
	default AccountBalance transaction(Long accountId, Transaction transaction) {
		switch (transaction.getTipo()) {
		case CREDIT:
			return credit(accountId, transaction);
		case DEBIT:
			return debit(accountId, transaction);
		default:
			throw new IllegalArgumentException("Invalid operation: " + transaction.getTipo());
		}
	}
	
	AccountBalance credit(Long accountId, Transaction transaction);
	AccountBalance debit(Long accountId, Transaction transaction);
}
