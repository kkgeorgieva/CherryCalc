package com.dxc.cherry.classes;

import java.util.HashMap;

import com.dxc.cherry.classes.parser.OperationProvider;
import com.dxc.cherry.classes.parser.Token;

public class CalculatorBuilder {
	
	private HashMap<String, Token> tokenMap = new HashMap<>();
	private HashMap<Token, OperationProvider> tokenToClassMap = new HashMap<>();
	
	public CalculatorBuilder() {
		tokenMap.put("(", new Token("(", 0));
		tokenMap.put(")", new Token(")", 0));
	}
	
	public CalculatorBuilder include(OperationProvider factory, int priority) {
		Token token = new Token(factory.operationSignature(), priority);
		tokenMap.put(factory.operationSignature(), token);
		tokenToClassMap.put(token, factory);
		return this;
	}
	
	public Calculator build() {
		return new Calculator(tokenMap, tokenToClassMap);
	}
}
