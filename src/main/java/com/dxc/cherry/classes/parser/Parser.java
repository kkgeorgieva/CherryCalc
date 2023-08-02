package com.dxc.cherry.classes.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.dxc.cherry.classes.parser.Token.Type;
import com.dxc.cherry.exceptions.InvalidExpressionException;

public class Parser {

	private int pos = 0;
	private Token curr_token = null;
	private final List<Token> tokens;
	private final HashMap<Token.Type,ASTNode> tokenToClassMap;

	public Parser(List<Token> tokens, HashMap<Type, ASTNode> tokenToClassMap2) {
		this.tokens = tokens;
		this.tokenToClassMap = tokenToClassMap2;
		getNext();
	}

	public ASTNode parseExpression() throws InvalidExpressionException {
		Stack<ASTNode> nodeStack = new Stack<>();
		Stack<Token> opStack = new Stack<>();

		while (curr_token.getType() != Token.Type.END_OF_INPUT) {
			if (curr_token.getType() == Token.Type.NUMBER) {
				nodeStack.push(new ASTLeaf(Float.parseFloat(curr_token.getLexeme())));
			} else if (curr_token.getType() == Token.Type.LEFT_PAREN) {
				opStack.push(curr_token);
			} else if (curr_token.getType() == Token.Type.RIGHT_PAREN) {
				while (!opStack.isEmpty() && opStack.peek().getType() != Token.Type.LEFT_PAREN) {
					Token opToken = opStack.pop();
					ASTNode opClass = tokenToClassMap.get(opToken.getType());
					ASTNode rightNode = nodeStack.pop();
					ASTNode leftNode = nodeStack.pop();
					nodeStack.push(createNewNode(opClass, leftNode, rightNode));
				}
				if (opStack.isEmpty()) {
					throw new InvalidExpressionException("Mismatched parentheses");
				}
				opStack.pop(); // remove left parenthesis
			} else if (curr_token.isOperator()) {
				while (!opStack.isEmpty() && opStack.peek().hasHigherPrecedence(curr_token)) {
					Token opToken = opStack.pop();
					ASTNode opClass = tokenToClassMap.get(opToken.getType());
					ASTNode rightNode = nodeStack.pop();
					ASTNode leftNode = nodeStack.pop();
					nodeStack.push(createNewNode(opClass, leftNode, rightNode));
				}
				opStack.push(curr_token);
			} else {
				throw new InvalidExpressionException("Invalid token: " + curr_token);
			}

			getNext();
		}

		while (!opStack.isEmpty()) {
			Token opToken = opStack.pop();
			if (opToken.getType() == Token.Type.LEFT_PAREN || opToken.getType() == Token.Type.RIGHT_PAREN) {
				throw new InvalidExpressionException("Mismatched parentheses");
			}
			ASTNode opClass = tokenToClassMap.get(opToken.getType());
			if (nodeStack.size() < 2) {
				throw new InvalidExpressionException("Invalid expression");
			}
			ASTNode rightNode = nodeStack.pop();
			ASTNode leftNode = nodeStack.pop();
			nodeStack.push(createNewNode(opClass, leftNode, rightNode));
		}

		if (nodeStack.size() != 1) {
			throw new InvalidExpressionException("Invalid expression");
		}

		return nodeStack.pop();
	}

	private ASTNode createNewNode(ASTNode opClass, ASTNode left, ASTNode right)
			throws InvalidExpressionException {
		try {
			return opClass.createInstance(left, right);
		} catch (Exception e) {
			throw new InvalidExpressionException("Error creating new instance of AST node");
		}
	}

	private void getNext() {
		if (pos < tokens.size()) {
			curr_token = tokens.get(pos);
			pos++;
		} else {
			curr_token = new Token(Token.Type.END_OF_INPUT, "");
		}
	}

}
