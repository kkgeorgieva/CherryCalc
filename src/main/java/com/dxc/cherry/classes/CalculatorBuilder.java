package com.dxc.cherry.classes;

import java.util.HashMap;

import com.dxc.cherry.classes.parser.ASTNode;
import com.dxc.cherry.classes.parser.Token;

public class CalculatorBuilder {
	
	private HashMap<String, Token> tokenMap = new HashMap<>();
	private HashMap<Token, ASTNode> tokenToClassMap = new HashMap<>();
	
	public CalculatorBuilder() {
		tokenMap.put("(", new Token("(", 0));
		tokenMap.put(")", new Token(")", 0));
	}
	
	public CalculatorBuilder include(ASTNode factory, int priority) {
		Token token = new Token(factory.getSignature(), priority);
		tokenMap.put(factory.getSignature(), token);
		tokenToClassMap.put(token, factory);
		return this;
	}
	
	public Calculator build() {
		return new Calculator(tokenMap, tokenToClassMap);
	}
}
