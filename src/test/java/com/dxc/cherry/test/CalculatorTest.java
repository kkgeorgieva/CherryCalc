package com.dxc.cherry.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dxc.cherry.classes.Calculator;
import com.dxc.cherry.classes.parser.Lexer;
import com.dxc.cherry.classes.parser.Parser;
import com.dxc.cherry.classes.parser.Token;
import com.dxc.cherry.exceptions.InvalidExpressionException;
import com.dxc.cherry.exceptions.InvalidOperationException;

class CalculatorTest {

	@Test void ExampleExpressionShouldThrowInvalidOperationException() { //given
	  String exampleExpression = "3+5*(7-1)/(7#8)"; 
	  Calculator calc = new Calculator();
	  assertThrows(InvalidOperationException.class, () ->
	  {calc.calculate(exampleExpression);}); 
	}

	@Test
	void InvalidExpressionExceptionMissingBracketTest() {
		String exampleExpression = "3+5)*(7-1)/(7+8)";
		Calculator calc = new Calculator();
		assertThrows(InvalidExpressionException.class, () -> { calc.calculate(exampleExpression); });
	}

	@Test
	void InvalidExpressionExceptionMissingOperandTest() {
		String exampleExpression = "3+5*(7-1)/(7+)";
		Calculator calc = new Calculator();
		assertThrows(InvalidExpressionException.class, () -> {calc.calculate(exampleExpression); });
	}

	@Test
	void TestTokens() throws InvalidOperationException, InvalidExpressionException {
		Lexer lexer = new Lexer("5 + 4.5 + 7");
		ArrayList<Token> tokens = (ArrayList<Token>) lexer.getTokens();
		String expected = "Token{type=NUMBER, lexeme='5'}\n" + "Token{type=PLUS, lexeme='+'}\n"
				+ "Token{type=NUMBER, lexeme='4.5'}\n" + "Token{type=PLUS, lexeme='+'}\n"
				+ "Token{type=NUMBER, lexeme='7'}\n" + "Token{type=END_OF_INPUT, lexeme='\0'}\n";
		String actual = lexer.toString();
		assertFalse(tokens.isEmpty());
		assertEquals(expected, actual);
	}

	@Test
	void CorrectCalculatorTest() throws Exception {
		Calculator calc = new Calculator();
		assertEquals(5, calc.calculate("3+5*(7-1)/(7+8)"));
	}
	
	@Test
	void ReturnResultTest() throws Exception {
		Calculator calc = new Calculator();
		calc.calculate("3+5*(7-1)/(7+8)");
		assertEquals(5, calc.returnResult());
	}
	
	@Test
	void TestDivideByZero() throws Exception {
		Calculator calc = new Calculator();
		assertThrows(ArithmeticException.class, () -> {calc.calculate("3+5/(7-7)*(8-7)");});
	}
}
