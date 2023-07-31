package com.dxc.cherry.classes.parser;

import java.util.List;

public class Parser {

	private int pos = 0;
	private Token curr_token = null;
	private final List<Token> tokens;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		getNext();
	}

	public ASTNode parseExpression() throws Exception {

		ASTNode result = Factor();

		while (curr_token.getType() != Token.Type.END_OF_INPUT  &&
				(curr_token.getType() == Token.Type.PLUS || curr_token.getType() == Token.Type.MINUS)) {
			if (curr_token.getType() == Token.Type.PLUS) {
				getNext();
				ASTNode rightNode = Factor();
				result =  new ASTNode("+", result, rightNode);
			} else if (curr_token.getType() == Token.Type.MINUS) {
				getNext();
				ASTNode rightNode = Factor();
				result =  new ASTNode("-", result, rightNode);
			}
		}
		return result;

//      5 + (7 * 4 + 4) * 4
	}

	public ASTNode Factor() throws Exception {
		ASTNode factor = Term();

		while (curr_token.getType() != Token.Type.END_OF_INPUT &&
				(curr_token.getType() == Token.Type.MULTIPLY || curr_token.getType() == Token.Type.DIVIDE)) {
			if (curr_token.getType() == Token.Type.MULTIPLY) {
				getNext();
				ASTNode rightNode = Term();
				factor =  new ASTNode("*", factor, rightNode);
			} else if (curr_token.getType() == Token.Type.DIVIDE) {
				getNext();
				ASTNode rightNode = Term();
				factor =  new ASTNode("/", factor, rightNode);
			}
		}
		
		return factor;
	}

	public ASTNode Term() throws Exception {
		ASTNode term = null;

		if (curr_token.getType() == Token.Type.LEFT_PAREN) {
			getNext();
			term = parseExpression();
			if (curr_token.getType() != Token.Type.RIGHT_PAREN) {
				throw new Exception();
			}
		} else if (curr_token.getType() == Token.Type.NUMBER) {
			term = new ASTNode(curr_token.getLexeme(), null, null);
		}
		getNext();
		return term;
	}

	private void getNext() {
		if (pos < tokens.size()) {
			curr_token = tokens.get(pos);
			pos++;
		}
	}

}
