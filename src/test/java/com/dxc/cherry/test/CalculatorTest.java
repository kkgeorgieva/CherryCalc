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
import com.dxc.cherry.classes.parser.ASTSum;
import com.dxc.cherry.classes.parser.Lexer;
import com.dxc.cherry.classes.parser.Parser;
import com.dxc.cherry.classes.parser.Token;
import com.dxc.cherry.exceptions.InvalidExpressionException;
import com.dxc.cherry.exceptions.InvalidOperationException;

class CalculatorTest {
	

	@Test void ExampleExpressionShouldThrowInvalidOperationException() {
	  Calculator calc = Calculator.builder.include(new ASTSum(null, null), 0)
								  .include(new ASTMultiply(null, null), 1)
								  .include(new ASTMinus(null, null), 1)
								  .include(new ASTDivide(null, null), 0).build();
	  String exampleExpression = "3+5*(7-1)/(7#8)"; 
	  assertThrows(InvalidOperationException.class, () ->
	  {calc.calculate(exampleExpression);}); 
	}

	@Test
	void InvalidExpressionExceptionMissingBracketTest() {
		Calculator calc = Calculator.builder.include(new ASTSum(null, null), 0)
				  .include(new ASTMultiply(null, null), 1)
				  .include(new ASTMinus(null, null), 1)
				  .include(new ASTDivide(null, null), 0).build();
		String exampleExpression = "3+5)*(7-1)/(7+8)";
		assertThrows(InvalidExpressionException.class, () -> { calc.calculate(exampleExpression); });
	}

	@Test
	void InvalidExpressionExceptionMissingOperandTest() {
		Calculator calc = Calculator.builder.include(new ASTSum(null, null), 0)
				  .include(new ASTMultiply(null, null), 1)
				  .include(new ASTMinus(null, null), 1)
				  .include(new ASTDivide(null, null), 0).build();
		String exampleExpression = "3+5*(7-1)/(7+)";
		assertThrows(InvalidExpressionException.class, () -> {calc.calculate(exampleExpression); });
	}

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
		Calculator calc = Calculator.builder.include(new ASTSum(null, null), 0).include(new ASTMultiply(null, null), 1).build();
		assertEquals(12, calc.calculate("(2+2)*3"));
	}
	
	@Test
	void ReturnResultTest() throws Exception {
		Calculator calc = Calculator.builder.include(new ASTSum(null, null), 0)
				  .include(new ASTMultiply(null, null), 1)
				  .include(new ASTMinus(null, null), 1)
				  .include(new ASTDivide(null, null), 0).build();		
		calc.calculate("3+5*(7-1)/(7+8)");
		assertEquals(5, calc.returnResult());
	}
	
	@Test
	void TestDivideByZero() throws Exception {
		Calculator calc = Calculator.builder.include(new ASTSum(null, null), 0)
				  .include(new ASTMultiply(null, null), 1)
				  .include(new ASTMinus(null, null), 1)
				  .include(new ASTDivide(null, null), 0).build();
		assertThrows(ArithmeticException.class, () -> {calc.calculate("3+5/(7-7)*(8-7)");});
	}
}
