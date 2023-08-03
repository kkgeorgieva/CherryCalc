package com.dxc.cherry.classes.Calculator;

import java.util.HashMap;

import com.dxc.cherry.classes.parser.Token;
import com.dxc.cherry.interfaces.parser.OperationProvider;

/**
 * This class builds an instance of the Calculator class.
 * Usage: Repeatedly call the include() method to add different operations to the Calculator 
 * and then call the build() method to create the Calculator instance.
 * 
 */
public class CalculatorBuilder {
	
	private HashMap<String, Token> tokenMap = new HashMap<>();
	private HashMap<Token, OperationProvider> tokenToClassMap = new HashMap<>();
	
	/**
	 * The CalculatorBuilder() should not be used to create an instance of the builder.
	 */
	public CalculatorBuilder() {
		tokenMap.put("(", new Token("(", 0));
		tokenMap.put(")", new Token(")", 0));
	}
	/**
	 * The "include()" method adds different operations to the Calculator instance.
	 * @param factory Represents the operation to be added to the Calculator.
	 * @param priority Represents the order in which the operations should be computed(user-defined).
	 */
	public CalculatorBuilder include(OperationProvider factory, int priority) {
		Token token = new Token(factory.operationSignature(), priority);
		tokenMap.put(factory.operationSignature(), token);
		tokenToClassMap.put(token, factory);
		return this;
	}
	/**
	 * 
	 * @return Returns the new Calculator instance.
	 */
	public Calculator build() {
		return new Calculator(tokenMap, tokenToClassMap);
	}
}
