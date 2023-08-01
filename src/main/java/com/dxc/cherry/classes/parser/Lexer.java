package com.dxc.cherry.classes.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.dxc.cherry.classes.parser.Token.Type;
import com.dxc.cherry.exceptions.InvalidOperationException;

/**
 * The Lexer class performs lexical analysis on a given input expression and converts it into a list of tokens.
 */
public class Lexer {
	private final List<Token> tokens;
	private final List<Character> numberList;
	private final String input;
	private int currentPos;
	private char currentCh;
	
	private final HashMap<Character, Token> tokenMap;
	
	  /**
     * Constructs a Lexer object with the specified input expression.
     *
     * @param input The input expression to be analyzed and converted into tokens.
     */
	public Lexer(String input, HashMap<Character, Token> tokenMap) {
		this.input = input;
		this.tokens = new ArrayList<>();
		this.numberList = new ArrayList<>(Arrays.asList('.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
		this.currentPos = 0;
		this.currentCh = input.length() > 0 ? this.input.charAt(currentPos) : '\0';
		this.tokenMap = tokenMap;
	}

	/**
	 * The "getNext" method iterates throughout the input string and changes the current position by moving to the next.
	 * 
	 */
	private void getNext() {
		if (currentPos < this.input.length() - 1) {
			currentPos++;
			this.currentCh = this.input.charAt(currentPos);
		} else
			currentCh = '\0';
	}

	//JavaDoc
	/*The "generateNumber" method is used to generate a token for a numerical value in the input expression.
	 *It iterates through the input characters and creates numbers by concatenating digits with one decimal point.
	 * @return Returns a new Token object with a numerical value.
	 * @throws InvalidOperationException If the input contains more than one decimal point in a number.
	 */
	private Token generateNumber() throws InvalidOperationException {
		int decCount = 0;
		StringBuilder sb = new StringBuilder();
		while (numberList.contains(currentCh)) {
			if (currentCh == '.' && decCount < 1) {
				decCount++;
			} else if (currentCh == '.') {
				throw new InvalidOperationException(String.format("%c is not allowed more than once.", currentCh));
			}
			if (sb.length() < 1 && decCount > 0)
				sb.append('0');
			sb.append(currentCh);
			getNext();
		}
		return new Token(Type.NUMBER, sb.toString());
	}

	/*
	 * The method "getTokens" is used to generate a list of tokens from the input expression.
	 * It iterates through the input characters and identifies different types of tokens based on their properties.
	 * The recognized tokens include numbers, arithmetic operators (+, -, *, /), parentheses (,), and the end of input.
	 * @return A List of Token objects representing the tokens found in the input expression.
	 * @throws InvalidOperationException If the input contains unsupported characters or invalid numerical values.
	 */
	public List<Token> getTokens() throws InvalidOperationException {
		while (currentPos < input.length()) {
			if (Character.isWhitespace(currentCh)) {
				getNext();
			} else if (numberList.contains(currentCh)) {
				tokens.add(generateNumber());
			} else if (tokenMap.containsKey(currentCh)) {
				tokens.add(tokenMap.get(currentCh));
				getNext();
			} else if (currentCh == '\0') {
				break;
			} else
				throw new InvalidOperationException(String.format("%c is unsupported type.", currentCh));
//			getNext();
		}

		tokens.add(new Token(Token.Type.END_OF_INPUT, "\0"));
		return tokens;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Token token : tokens) {
			str.append(token.toString() + '\n');
		}
		return str.toString();
	}
}
