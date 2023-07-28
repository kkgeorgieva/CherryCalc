package com.dxc.cherry.classes.parser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String expression = "5 + 4 + 7";
        Parser parser = new Parser(expression);
        List<Token> tokens = parser.parse();

        for (Token token : tokens) {
            System.out.println(token);
        }

        ASTNode ast = parser.parseExpression(tokens);
        System.out.println(ast.toString());
    }
}
