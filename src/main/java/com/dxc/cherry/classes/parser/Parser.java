package com.dxc.cherry.classes.parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final String input;
    private int currentPos;
    private final List<Token> tokens;

    public Parser(String input) {
        this.input = input;
        this.currentPos = 0;
        this.tokens = new ArrayList<>();
    }

    public List<Token> parse() {
        while (currentPos < input.length()) {
            char currentChar = input.charAt(currentPos);

            if (currentChar == '+') {
                tokens.add(new Token(Token.Type.PLUS, "+"));
                currentPos++;
            } else if (currentChar == '-') {
                tokens.add(new Token(Token.Type.MINUS, "-"));
                currentPos++;
            } else if (currentChar == '*') {
                tokens.add(new Token(Token.Type.MULTIPLY, "*"));
                currentPos++;
            } else if (currentChar == '/') {
                tokens.add(new Token(Token.Type.DIVIDE, "/"));
                currentPos++;
            }

            else if (currentChar == '(') {
                tokens.add(new Token(Token.Type.LEFT_PAREN, "("));
                currentPos++;
            } else if (currentChar == ')') {
                tokens.add(new Token(Token.Type.RIGHT_PAREN, ")"));
                currentPos++;
            }

            else if (Character.isDigit(currentChar)) {
                int start = currentPos;
                while (currentPos < input.length() && Character.isDigit(input.charAt(currentPos))) {
                    currentPos++;
                }
                tokens.add(new Token(Token.Type.NUMBER, input.substring(start, currentPos)));
            }

            else if (Character.isWhitespace(currentChar)) {
                currentPos++;
            } else {
                throw new IllegalArgumentException("Invalid character at position " + currentPos);
            }
        }

        // Add the END_OF_INPUT token
        // tokens.add(new Token(Token.Type.END_OF_INPUT, ""));

        return tokens;
    }

    public ASTNode parseExpression(List<Token> tokens) {
        
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == Token.Type.PLUS) {
                return new ASTNode("+", parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i + 1, tokens.size()))); 
            } else if (tokens.get(i).getType() == Token.Type.NUMBER && tokens.size() == 1) {
                return new ASTNode(tokens.get(i).getLexeme());
            }
        }
        return null;
    }
}
