package com.dxc.cherry.classes.parser;


public interface OperationProvider {
	Operation getInstance(Operation left, Operation right);
	String operationSignature();
}
