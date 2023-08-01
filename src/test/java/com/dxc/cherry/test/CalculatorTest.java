package com.dxc.cherry.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dxc.cherry.classes.Calculator;
import com.dxc.cherry.classes.parser.ASTDivide;
import com.dxc.cherry.classes.parser.ASTMinus;
import com.dxc.cherry.classes.parser.ASTMultiply;
import com.dxc.cherry.classes.parser.ASTNode;
import com.dxc.cherry.classes.parser.ASTPower;
import com.dxc.cherry.classes.parser.ASTSum;
import com.dxc.cherry.classes.parser.Lexer;
import com.dxc.cherry.classes.parser.Parser;
import com.dxc.cherry.classes.parser.Token;
import com.dxc.cherry.exceptions.InvalidExpressionException;
import com.dxc.cherry.exceptions.InvalidOperationException;

class CalculatorTest {
	
	HashMap<Character, Token> setupTokenMap() {
		HashMap<Character, Token> tokenMap = new HashMap<Character, Token>();
		tokenMap.put('+', new Token(Token.Type.PLUS, "+"));
		tokenMap.put('-', new Token(Token.Type.MINUS, "-"));
		tokenMap.put('*', new Token(Token.Type.MULTIPLY, "*"));
		tokenMap.put('/', new Token(Token.Type.DIVIDE, "/"));
		tokenMap.put('(', new Token(Token.Type.LEFT_PAREN, "("));
		tokenMap.put(')', new Token(Token.Type.RIGHT_PAREN, ")"));
		tokenMap.put('^', new Token(Token.Type.POWER, "^"));
		return tokenMap;
	}
	
	HashMap<Token.Type, Class<? extends ASTNode>> setupTokenToClassMap() {
		HashMap<Token.Type, Class<? extends ASTNode>> operationsMap = new HashMap<Token.Type, Class<? extends ASTNode>>();
		operationsMap.put(Token.Type.PLUS, ASTSum.class);
		operationsMap.put(Token.Type.MINUS, ASTMinus.class);
		operationsMap.put(Token.Type.MULTIPLY, ASTMultiply.class);
		operationsMap.put(Token.Type.DIVIDE, ASTDivide.class);
		operationsMap.put(Token.Type.POWER, ASTPower.class);

		return operationsMap;
	}

//	@Test void ExampleExpressionShouldThrowInvalidOperationException() { //given
//	  String exampleExpression = "3+5*(7-1)/(7#8)"; 
//	  Calculator calc = new Calculator(setupTokenMap(), setupTokenToClassMap());
//	  assertThrows(InvalidOperationException.class, () ->
//	  {calc.calculate(exampleExpression);}); 
//	}
//
//	@Test
//	void InvalidExpressionExceptionMissingBracketTest() {
//		String exampleExpression = "3+5)*(7-1)/(7+8)";
//		Calculator calc = new Calculator(setupTokenMap(), setupTokenToClassMap());
//		assertThrows(InvalidExpressionException.class, () -> { calc.calculate(exampleExpression); });
//	}
//
//	@Test
//	void InvalidExpressionExceptionMissingOperandTest() {
//		String exampleExpression = "3+5*(7-1)/(7+)";
//		Calculator calc = new Calculator(setupTokenMap(), setupTokenToClassMap());
//		assertThrows(InvalidExpressionException.class, () -> {calc.calculate(exampleExpression); });
//	}
//
//	@Test
//	void TestTokens() throws InvalidOperationException, InvalidExpressionException {
//		Lexer lexer = new Lexer("5 + 4.5 + 7", setupTokenMap());
//		ArrayList<Token> tokens = (ArrayList<Token>) lexer.getTokens();
//		String expected = "Token{type=NUMBER, lexeme='5'}\n" + "Token{type=PLUS, lexeme='+'}\n"
//				+ "Token{type=NUMBER, lexeme='4.5'}\n" + "Token{type=PLUS, lexeme='+'}\n"
//				+ "Token{type=NUMBER, lexeme='7'}\n" + "Token{type=END_OF_INPUT, lexeme='\0'}\n";
//		String actual = lexer.toString();
//		assertFalse(tokens.isEmpty());
//		assertEquals(expected, actual);
//	}

	@Test
	void CorrectCalculatorTest() throws Exception {
		Calculator calc = new Calculator(setupTokenMap(), setupTokenToClassMap());
		assertEquals(1, calc.calculate("2*2 / 2^2"));
	}
	
//	@Test
//	void ReturnResultTest() throws Exception {
//		Calculator calc = new Calculator(setupTokenMap(), setupTokenToClassMap());
//		calc.calculate("3+5*(7-1)/(7+8)");
//		assertEquals(5, calc.returnResult());
//	}
//	
//	@Test
//	void TestDivideByZero() throws Exception {
//		Calculator calc = new Calculator(setupTokenMap(), setupTokenToClassMap());
//		assertThrows(ArithmeticException.class, () -> {calc.calculate("3+5/(7-7)*(8-7)");});
//	}
}
