package com.study.exception;

public class AccountNotFoundException extends BusinessException {
	private static final long serialVersionUID = 8093964368685298872L;

	public AccountNotFoundException(String message) {
		super(404, message);
	}

}
