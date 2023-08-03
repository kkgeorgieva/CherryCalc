package com.dxc.cherry.interfaces.parser;

/**
 * This interface that needs to be implemented by every operation.
 */
public interface OperationProvider {
	
	Operation getInstance(Operation left, Operation right);
	String operationSignature();
}
