package com.study.service;

import com.study.model.Statement;

public interface CustomerStatement {
	
	public Statement statement(Long customerId);
}
