package com.study.service;

import com.study.model.Statement;

import io.smallrye.mutiny.Uni;

public interface CustomerStatement {
	
	public Uni<Statement> statement(Long customerId);
}
