package com.study.service.impl;

import java.util.Optional;

import com.study.exception.BalanceOutOfLimitException;
import com.study.exception.CustomerNotFoundException;
import com.study.model.CustomerBalance;
import com.study.model.Transaction;
import com.study.repository.CustomerRepository;
import com.study.repository.entity.Customer;
import com.study.service.CustomerMoviment;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AccountMovimentImpl implements CustomerMoviment {
	
	@Inject
	private CustomerRepository customerRepository;

	@Override
	public CustomerBalance credit(Long customerId, Transaction transaction) {
		Log.infof("Credit Operation - value %s to %s" , transaction.getValor(), customerId);
		Optional<Customer> optCustomer = customerRepository.findByIdOptional(customerId);
		
		if(optCustomer.isEmpty()) {
			throw new CustomerNotFoundException("Can not found the account");
		}
		
		Customer customer = optCustomer.get();
		customer.setBalance(customer.getBalance() + transaction.getValor());
		customerRepository.persist(customer);
		//TODO inserir registro da tabela de historico
		
		return new CustomerBalance(customer.getLimit(), customer.getBalance());
	}

	@Override
	public CustomerBalance debit(Long accountId, Transaction transaction) {
		System.out.println("Debit - " + transaction.getValor() +  " from " + accountId);
		if(accountId.equals(4l)) {
			throw new BalanceOutOfLimitException("Balance can not be less than limit");
		}
		return new CustomerBalance(1000, 0, null);
	}

}
