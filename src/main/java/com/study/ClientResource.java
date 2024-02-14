package com.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.model.AccountBalance;
import com.study.model.Transaction;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clientes")
public class ClientResource {
	
	@POST
	@Path("/{accountId}/transacoes")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountBalance accountDebitOrCreditOperation(@PathParam("accountId")Long accountId, Transaction transaction) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("Accountid: " + accountId + "\n" + objectMapper.writeValueAsString(transaction));
		return new AccountBalance(1000, 500, null);
	}

}
