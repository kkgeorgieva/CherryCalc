package com.dxc.cherry.classes.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.dxc.cherry.exceptions.InvalidExpressionException;
import com.dxc.cherry.interfaces.parser.Operation;
import com.dxc.cherry.interfaces.parser.OperationProvider;
/**
 * The "Parser" class accepts a list of tokens computed by the "Lexer" class and creates and abstract 
 * syntax tree using the different operations and numbers, inputed by the user.
 */
public class Parser {

	private int pos = 0;
	private Token curr_token = null;
	private final List<Token> tokens;
	private final HashMap<Token, OperationProvider> tokenToClassMap;
	private final Stack<Operation> nodeStack = new Stack<>();
	private final Stack<Token> opStack = new Stack<>();
/**
 * 
 * @param tokens The list of tokens representing the expression.
 * @param tokenToClassMap A map containing the association between tokens and operation providers.
 */
	public Parser(List<Token> tokens, HashMap<Token, OperationProvider> tokenToClassMap) {
		this.tokens = tokens;
		this.tokenToClassMap = tokenToClassMap;
		getNext();
	}
/**
 * 
 * @return Returns the the abstract syntax tree, representing the whole expression.
 * @throws InvalidExpressionException Throws an exception when an invalid token is passed.
 */
	public Operation parseExpression() throws InvalidExpressionException {

		while (curr_token.getLexeme() != "\0") {
			if (!curr_token.isOperator()) {
				nodeStack.push(new Number(Float.parseFloat(curr_token.getLexeme())));
			} else if (curr_token.getLexeme() == "(") {
				opStack.push(curr_token);
			} else if (curr_token.getLexeme() == ")") {
				bracketsHandler();
			} else if (curr_token.isOperator()) {
				precedenceSorter();
			} else {
				throw new InvalidExpressionException("Invalid token: " + curr_token);
			}

			getNext();
		}

		treeCreator();

		if (nodeStack.size() != 1) {
			throw new InvalidExpressionException("Invalid expression");
		}

		return nodeStack.pop();
	}

	private void treeCreator() throws InvalidExpressionException {
		while (!opStack.isEmpty()) {
			Token opToken = opStack.pop();
			if (opToken.getLexeme() == "(" || opToken.getLexeme() == ")") {
				throw new InvalidExpressionException("Mismatched parentheses");
			}
			OperationProvider opClass = tokenToClassMap.get(opToken);
			if (nodeStack.size() < 2) {
				throw new InvalidExpressionException("Invalid expression");
			}
			Operation rightNode = nodeStack.pop();
			Operation leftNode = nodeStack.pop();
			nodeStack.push(createNewNode(opClass, leftNode, rightNode));
		}
	}

	private void bracketsHandler() throws InvalidExpressionException {
		while (!opStack.isEmpty() && opStack.peek().getLexeme() != "(") {
			Token opToken = opStack.pop();
			OperationProvider opClass = tokenToClassMap.get(opToken);
			Operation rightNode = nodeStack.pop();
			Operation leftNode = nodeStack.pop();
			nodeStack.push(createNewNode(opClass, leftNode, rightNode));
		}
		if (opStack.isEmpty()) {
			throw new InvalidExpressionException("Mismatched parentheses");
		}
		opStack.pop();
	}

	private void precedenceSorter() throws InvalidExpressionException {
		while (!opStack.isEmpty() && opStack.peek().hasHigherPrecedence(curr_token)) {
			Token opToken = opStack.pop();
			OperationProvider opClass = tokenToClassMap.get(opToken);
			Operation rightNode = nodeStack.pop();
			Operation leftNode = nodeStack.pop();
			nodeStack.push(createNewNode(opClass, leftNode, rightNode));
		}
		opStack.push(curr_token);
	}

	private Operation createNewNode(OperationProvider opClass, Operation left, Operation right)
			throws InvalidExpressionException {
		try {
			return opClass.getInstance(left, right);
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
