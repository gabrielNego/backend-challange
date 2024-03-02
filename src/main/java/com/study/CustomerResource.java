package com.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.model.CustomerBalance;
import com.study.model.Statement;
import com.study.model.Transaction;
import com.study.service.CustomerMoviment;
import com.study.service.CustomerStatement;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/clientes")
public class CustomerResource {
	
	@Inject
	CustomerMoviment accountMoviment;
	
	@Inject
	CustomerStatement customerStatement;
	
	@POST
	@Path("/{customerId}/transacoes")
	@Produces(MediaType.APPLICATION_JSON)
	@WithTransaction
	public Uni<CustomerBalance> customerDebitOrCreditOperation(@PathParam("customerId")Long customerId, @Valid Transaction transaction) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Log.debugf("Customer: %s \n %s", transaction, objectMapper.writeValueAsString(transaction));
		return accountMoviment.transaction(customerId, transaction);
	}
	
	@GET
	@Path("/{customerId}/extrato")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Statement> accountStatement(@PathParam("customerId") Long customerId) {
		return customerStatement.statement(customerId);
	}

}
