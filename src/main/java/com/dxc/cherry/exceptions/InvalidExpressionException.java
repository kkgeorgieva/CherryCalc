package com.dxc.cherry.exceptions;

public class InvalidExpressionException extends Exception {

	public InvalidExpressionException(String errorMessage) {
		super(errorMessage);
	}

	private static final long serialVersionUID = 3159034685771832076L;
}
