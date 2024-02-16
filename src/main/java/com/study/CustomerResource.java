package com.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.model.AccountBalance;
import com.study.model.Statement;
import com.study.model.Transaction;
import com.study.service.AccountMoviment;
import com.study.service.impl.AccountMovimentImpl;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clientes")
public class CustomerResource {
	
	//TODO temp
	private AccountMoviment accountMoviment = new AccountMovimentImpl();
	
	@POST
	@Path("/{customerId}/transacoes")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountBalance accountDebitOrCreditOperation(@PathParam("customerId")Long customerId, Transaction transaction) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("CustomerId: " + customerId + "\n" + objectMapper.writeValueAsString(transaction));
		return accountMoviment.transaction(customerId, transaction);
	}
	
	@GET
	@Path("/{customerId}/extrato")
	@Produces(MediaType.APPLICATION_JSON)
	public Statement accountStatement(@PathParam("customerId") Long customerId) {
		return new Statement();
	}

}
