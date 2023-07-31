package com.dxc.cherry.classes.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.dxc.cherry.classes.parser.Token.Type;
import com.dxc.cherry.exceptions.InvalidOperationException;

//JavaDoc
public class Lexer {
	private final List<Token> tokens;
	private final List<Character> numberList;
	private final String input;
	private int currentPos;
	private char currentCh;

	public Lexer(String input) {
		this.input = input;
		this.tokens = new ArrayList<>();
		this.numberList = new ArrayList<>(Arrays.asList('.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
		this.currentPos = 0;
		this.currentCh = input.length() > 0 ? this.input.charAt(currentPos) : '\0';
	}

	//JavaDoc
	private void getNext() {
		if (currentPos < this.input.length() - 1) {
			currentPos++;
			this.currentCh = this.input.charAt(currentPos);
		} else
			currentCh = '\0';
	}

	//JavaDoc
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

	//JavaDoc
	public List<Token> getTokens() throws InvalidOperationException {
		while (currentPos < input.length()) {
			if (Character.isWhitespace(currentCh)) {
				getNext();
			} else if (numberList.contains(currentCh)) {
				tokens.add(generateNumber());
				
			} else if (currentCh == '+') {
				tokens.add(new Token(Token.Type.PLUS, "+"));
				getNext();
			} else if (currentCh == '-') {
				tokens.add(new Token(Token.Type.MINUS, "-"));
				getNext();
			} else if (currentCh == '*') {
				tokens.add(new Token(Token.Type.MULTIPLY, "*"));
				getNext();
			} else if (currentCh == '/') {
				tokens.add(new Token(Token.Type.DIVIDE, "/"));
				getNext();
			} else if (currentCh == '(') {
				tokens.add(new Token(Token.Type.LEFT_PAREN, "("));
				getNext();
			} else if (currentCh == ')') {
				tokens.add(new Token(Token.Type.RIGHT_PAREN, ")"));
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
	
	// A method that returns true if the input expression has balanced parentheses, and false otherwise
	public static boolean isBalanced(String expression) {
	  // Create a stack to store the opening parentheses
	  Stack<Character> stack = new Stack<>();
	  // Loop through each character in the expression
	  for (char c : expression.toCharArray()) {
	    // If the character is an opening parenthesis, push it to the stack
	    if (c == '(' || c == '{' || c == '[') {
	      stack.push(c);
	    }
	    // If the character is a closing parenthesis, pop the top element from the stack and check if it matches
	    else if (c == ')' || c == '}' || c == ']') {
	      // If the stack is empty, it means there is no matching opening parenthesis, so return false
	      if (stack.isEmpty()) {
	        return false;
	      }
	      // Pop the top element from the stack
	      char top = stack.pop();
	      // Check if it matches with the closing parenthesis
	      if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
	        // If not, it means the parentheses are not balanced, so return false
	        return false;
	      }
	    }
	  }
	  // After looping through all the characters, check if the stack is empty
	  // If yes, it means all the parentheses are balanced, so return true
	  // If no, it means there are some unmatched opening parentheses, so return false
	  return stack.isEmpty();
	}

}
