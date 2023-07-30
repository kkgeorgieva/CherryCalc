package com.dxc.cherry.classes.parser;

import java.util.ArrayList;
import java.util.List;

import com.dxc.cherry.exceptions.InvalidOperationException;

public class Main {
    public static void main(String[] args) {
        String expression = "5 + 7 * 4 + 4 * 4";
        Lexer lexer = new Lexer(expression);
        List<Token> tokens = new ArrayList<Token>();
		try {
			tokens = lexer.getTokens();
		} catch (InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        for (Token token : tokens) {
            System.out.println(token);
        }

        ASTNode ast = Parser.parseExpression(tokens);
        System.out.println(ast.toString());
    }
}
