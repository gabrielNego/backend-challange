package com.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.model.CustomerBalance;
import com.study.model.Statement;
import com.study.model.Transaction;
import com.study.service.CustomerMoviment;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clientes")
public class CustomerResource {
	
	@Inject
	private CustomerMoviment accountMoviment;
	
	@POST
	@Path("/{customerId}/transacoes")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public CustomerBalance customerDebitOrCreditOperation(@PathParam("customerId")Long customerId, Transaction transaction) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Log.debugf("Customer: %s \n %s", transaction, objectMapper.writeValueAsString(transaction));
		return accountMoviment.transaction(customerId, transaction);
	}
	
	@GET
	@Path("/{customerId}/extrato")
	@Produces(MediaType.APPLICATION_JSON)
	public Statement accountStatement(@PathParam("customerId") Long customerId) {
		return new Statement();
	}

}
