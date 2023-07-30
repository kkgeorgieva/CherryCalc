package com.dxc.cherry.classes.parser;

import java.util.List;

public class Parser {

    public static ASTNode parseExpression(List<Token> tokens) {
    	
//    	for (int i = 0; i < tokens.size(); i++) {
//    		if (tokens.get(i).getType() == Token.Type.LEFT_PAREN) {
//				
//			}
//    	}
        
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == Token.Type.PLUS) {
                return new ASTNode("+", parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i + 1, tokens.size()))); 
            } else if (tokens.get(i).getType() == Token.Type.MINUS) {
                return new ASTNode("-", parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i + 1, tokens.size()))); 
            }
        }
        
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getType() == Token.Type.MULTIPLY) {
                return new ASTNode("*", parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i + 1, tokens.size()))); 
            } else if (tokens.get(i).getType() == Token.Type.DIVIDE) {
                return new ASTNode("/", parseExpression(tokens.subList(0, i)), parseExpression(tokens.subList(i + 1, tokens.size()))); 
            }
        }
        
        for (int i = 0; i < tokens.size(); i++) {
        	if (tokens.get(i).getType() == Token.Type.NUMBER && tokens.size() == 1) {
                return new ASTNode(tokens.get(i).getLexeme());
            }
        }
        
        return null; //Should throw exception
    }
}
