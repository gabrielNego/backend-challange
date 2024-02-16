package com.study.exception;

public class BalanceOutOfLimitException extends BusinessException {
	private static final long serialVersionUID = 1213327472882065650L;

	public BalanceOutOfLimitException(String message) {
		super(422, message);
	}
}
