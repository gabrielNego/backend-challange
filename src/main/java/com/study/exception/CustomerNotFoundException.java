package com.study.exception;

public class CustomerNotFoundException extends BusinessException {
	private static final long serialVersionUID = 8093964368685298872L;

	public CustomerNotFoundException(String message) {
		super(404, message);
	}

}
