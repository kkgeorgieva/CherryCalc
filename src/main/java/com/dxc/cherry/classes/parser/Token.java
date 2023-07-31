package com.dxc.cherry.classes.parser;

//JavaDoc
public class Token {
    public enum Type {
        NUMBER,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        LEFT_PAREN,
        RIGHT_PAREN,
        END_OF_INPUT
    }

    private final Type type;
    private final String lexeme;

    public Token(Type type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    public Type getType() {
        return type;
    }

    
    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", lexeme='" + lexeme + '\'' +
                '}';
    }
}
