package com.dxc.cherry.classes.parser;

import java.util.List;

import com.dxc.cherry.exceptions.InvalidExpressionException;

//JavaDoc
public class Parser {

	private int pos = 0;
	private Token curr_token = null;
	private final List<Token> tokens;
	private boolean inBrackets = false;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		getNext();
	}

	//JavaDoc
	public ASTNode parseExpression() throws InvalidExpressionException {

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
		
		if (curr_token.getType() == Token.Type.RIGHT_PAREN && !inBrackets) {
			throw new InvalidExpressionException("Right bracket without an opening left bracket!");
		}
		
		return result;

	}

	//JavaDoc
	private ASTNode Factor() throws InvalidExpressionException {
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
		
		if (curr_token.getType() == Token.Type.RIGHT_PAREN && !inBrackets) {
			throw new InvalidExpressionException("Right bracket without an opening left bracket!");
		}
		
		return factor;
	}

	//JavaDoc
	private ASTNode Term() throws InvalidExpressionException {
		ASTNode term = null;
		
		if (curr_token.getType() == Token.Type.LEFT_PAREN) {
			inBrackets = true;
			getNext();
			term = parseExpression();
			if (curr_token.getType() != Token.Type.RIGHT_PAREN) {
				throw new InvalidExpressionException("Left bracket without a closing right bracket!");
			}
			inBrackets = false;
		} else if (curr_token.getType() == Token.Type.NUMBER) {
			term = new ASTNode(curr_token.getLexeme(), null, null);
		}
		getNext();
		return term;
	}

	//JavaDoc
	private void getNext() {
		if (pos < tokens.size()) {
			curr_token = tokens.get(pos);
			pos++;
		}
	}

}
