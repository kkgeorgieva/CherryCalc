package com.dxc.cherry.classes.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.dxc.cherry.exceptions.InvalidExpressionException;

public class Parser {

	private int pos = 0;
	private Token curr_token = null;
	private final List<Token> tokens;
	private final HashMap<String, Class<? extends ASTNode>> tokenToClassMap;

	public Parser(List<Token> tokens, HashMap<String, Class<? extends ASTNode>> tokenToClassMap) {
		this.tokens = tokens;
		this.tokenToClassMap = tokenToClassMap;
		getNext();
	}

	public ASTNode parseExpression() throws InvalidExpressionException {
		Stack<ASTNode> nodeStack = new Stack<>();
		Stack<Token> opStack = new Stack<>();

		while (curr_token.getLexeme() != "\0") {
			if (curr_token.isOperator()) {
				nodeStack.push(new ASTLeaf(Float.parseFloat(curr_token.getLexeme())));
			} else if (curr_token.getLexeme() == "(") {
				opStack.push(curr_token);
			} else if (curr_token.getLexeme() == ")") {
				while (!opStack.isEmpty() && opStack.peek().getLexeme() != "(") {
					Token opToken = opStack.pop();
					Class<? extends ASTNode> opClass = tokenToClassMap.get(opToken.getLexeme());
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
					Class<? extends ASTNode> opClass = tokenToClassMap.get(opToken.getLexeme());
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
			if (opToken.getLexeme() == "(" || opToken.getLexeme() == ")") {
				throw new InvalidExpressionException("Mismatched parentheses");
			}
			Class<? extends ASTNode> opClass = tokenToClassMap.get(opToken.getLexeme());
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

	private ASTNode createNewNode(Class<? extends ASTNode> opClass, ASTNode left, ASTNode right)
			throws InvalidExpressionException {
		try {
			return opClass.getDeclaredConstructor(ASTNode.class, ASTNode.class).newInstance(left, right);
		} catch (Exception e) {
			throw new InvalidExpressionException("Error creating new instance of AST node");
		}
	}

	private void getNext() {
		if (pos < tokens.size()) {
			curr_token = tokens.get(pos);
			pos++;
		} else {
			curr_token = new Token("\0");
		}
	}

}
