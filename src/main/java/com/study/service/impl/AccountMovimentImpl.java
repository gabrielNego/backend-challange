package com.study.service.impl;

import com.study.exception.AccountNotFoundException;
import com.study.exception.BalanceOutOfLimitException;
import com.study.model.AccountBalance;
import com.study.model.Transaction;
import com.study.service.AccountMoviment;

public class AccountMovimentImpl implements AccountMoviment {

	@Override
	public AccountBalance credit(Long accountId, Transaction transaction) {
		System.out.println("Credit - " + transaction.getValor() +  " to " + accountId);
		
		if(accountId.equals(4l)) {
			throw new AccountNotFoundException("Can not found the account");
		}
		
		return new AccountBalance(1000, transaction.getValor(), null);
	}

	@Override
	public AccountBalance debit(Long accountId, Transaction transaction) {
		System.out.println("Debit - " + transaction.getValor() +  " from " + accountId);
		if(accountId.equals(4l)) {
			throw new BalanceOutOfLimitException("Balance can not be less than limit");
		}
		return new AccountBalance(1000, 0, null);
	}

}
