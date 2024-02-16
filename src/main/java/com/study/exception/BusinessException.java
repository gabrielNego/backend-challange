package com.study.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 4750067852621444078L;
	private int httpStatus;

	protected BusinessException(int httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}
}
