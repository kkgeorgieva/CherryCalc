package com.dxc.cherry.classes;

import java.util.HashMap;
import java.util.List;

import com.dxc.cherry.classes.parser.Lexer;
import com.dxc.cherry.classes.parser.Operation;
import com.dxc.cherry.classes.parser.OperationProvider;
import com.dxc.cherry.classes.parser.Parser;
import com.dxc.cherry.classes.parser.Token;

/**
 * The calculator class is responsible for making evaluations of arithmetic expressions.
 * It performs a lexical analysis and parsing and creates an Abstract Syntax Tree, after which it evaluates the expression. 
 */
public class Calculator {

	private float result;
	private Lexer lexer;
	private Parser parser;
	private final HashMap<String, Token> tokenMap;
	private final HashMap<Token, OperationProvider> tokenToClassMap;
	public static CalculatorBuilder builder = new CalculatorBuilder();


	public Calculator(HashMap<String, Token> tokenMap, HashMap<Token, OperationProvider> tokenToClassMap) {
		this.tokenMap = tokenMap;
		this.tokenToClassMap = tokenToClassMap;
	}
	
	/**
	 * @param ex This string represents the given arithmetic expression to be evaluated.
	 * @return Returns the result of the evaluated expression.
	 * @throws Exception Throws an exception if an error occurs during the process of lexical analysis, parsing or evaluation.
	 */
	public float calculate(String ex) throws Exception {
		lexer = new Lexer(ex, tokenMap);
		List<Token> tokens = lexer.getTokens();
		
		parser = new Parser(tokens, tokenToClassMap);
		Operation tree = parser.parseExpression();
		
		result = tree.eval();
		
		return result;	
	}

	/**
	 * @returnReturns the result of the evaluated expression.
	 */
	public float returnResult() {
		return result;
	}
}
