package com.dxc.cherry.classes;

import java.util.List;

import com.dxc.cherry.classes.parser.ASTNode;
import com.dxc.cherry.classes.parser.Lexer;
import com.dxc.cherry.classes.parser.Parser;
import com.dxc.cherry.classes.parser.Token;

//JavaDoc
public class Calculator {

	private float result;
	private Lexer lexer;
	private Parser parser;

	//JavaDoc
	public float calculate(String ex) throws Exception {
		lexer = new Lexer(ex);
		List<Token> tokens = lexer.getTokens();
		
		parser = new Parser(tokens);
		ASTNode tree = parser.parseExpression();
		
		result = tree.eval();
		
		return result;	
	}

	//JavaDoc
	public float returnResult() {
		return result;
	}
}
