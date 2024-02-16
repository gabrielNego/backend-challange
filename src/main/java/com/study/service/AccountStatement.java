package com.study.service;

import com.study.model.Statement;

public interface AccountStatement {
	
	public Statement statement(Long accountId);
}
