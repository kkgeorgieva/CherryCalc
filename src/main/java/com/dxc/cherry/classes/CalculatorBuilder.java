package com.dxc.cherry.classes;

import java.util.HashMap;

import com.dxc.cherry.classes.parser.ASTNode;
import com.dxc.cherry.classes.parser.Token;

public class CalculatorBuilder {
	
	private HashMap<String, Token> tokenMap = new HashMap<>();
	private HashMap<Token.Type, ASTNode> tokenToClassMap = new HashMap<>();
	
	public CalculatorBuilder include(ASTNode factory, int priority) {
		tokenMap.put(factory.getSignature(), new Token(Token.Type.PLUS, factory.getSignature()));
		tokenToClassMap.put(Token.Type.PLUS, factory);
		return this;
	}
	
	public Calculator build() {
		return new Calculator(tokenMap, tokenToClassMap);
	}
}
