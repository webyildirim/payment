package com.acme.payment.exception;

public class EntityAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String errorCode;

	public EntityAlreadyExistException(String errorCode, Throwable exception) {
		super(exception);
		this.errorCode = errorCode;
	}

	public EntityAlreadyExistException(String errorCode) {
		this(errorCode, null);

	}

	public String getErrorCode() {
		return errorCode;
	}

}
