package com.dxc.cherry.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.dxc.cherry.classes.parser.Lexer;
import com.dxc.cherry.classes.parser.Token;
import com.dxc.cherry.exceptions.InvalidExpressionException;
import com.dxc.cherry.exceptions.InvalidOperationException;

class CalculatorTest {

	/*
	 * @Test void ExampleExpressionShouldEqual5() { String exampleExpression =
	 * "3+5*(7-1)/(7+8)"; Calculator calc = new Calculator(); assertEquals(5,
	 * calc.Calculate(exampleExpression)); }
	 * 
	 * @Test void DivOf1And0ShouldThrowArithemticException() {
	 * assertThrows(ArithmeticException, Div::calculate(1,0)); }
	 * 
	 * @Test void ExampleExpressionShouldThrowInvalidOperationException() { //given
	 * that # is not an implemented operation String exampleExpression =
	 * "3+5*(7-1)/(7#8)"; Calculator calc = new Calculator();
	 * assertThrows(InvalidOperationException.class,
	 * calc.Calculate(exampleExpression)); }
	 * 
	 * @Test void ExampleExpressionShouldThrowInvalidExpressionException() {
	 * //invalid bracket placement String exampleExpression = "3+5)*(7-1)/(7+8)";
	 * Calculator calc = new Calculator();
	 * assertThrows(InvalidExpressionException.class,
	 * calc.Calculate(exampleExpression)); }
	 * 
	 * @Test void ExampleExpressionShouldThrowInvalidExpressionException() {
	 * //missing left or right operand String exampleExpression = "3+5*(7-1)/(7+)";
	 * Calculator calc = new Calculator();
	 * assertThrows(InvalidExpressionException.class,
	 * calc.Calculate(exampleExpression)); }
	 */
	@Test
	void TestTokens() throws InvalidOperationException {
		Lexer lexer = new Lexer("5 + 4.5 + 7");
		ArrayList<Token> tokens = (ArrayList<Token>) lexer.getTokens();
		String expected = "Token{type=NUMBER, lexeme='5'}\n"
				+ "Token{type=PLUS, lexeme='+'}\n"
				+ "Token{type=NUMBER, lexeme='4.5'}\n"
				+ "Token{type=PLUS, lexeme='+'}\n"
				+ "Token{type=NUMBER, lexeme='7'}\n";
		String actual = lexer.toString();
		assertFalse(tokens.isEmpty());
		assertEquals(expected, actual);
	}
}
