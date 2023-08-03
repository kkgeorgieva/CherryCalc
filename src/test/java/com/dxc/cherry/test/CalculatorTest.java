package com.dxc.cherry.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dxc.cherry.classes.Calculator;
import com.dxc.cherry.classes.parser.buildInOperations.AdditionOpFactory;
import com.dxc.cherry.classes.parser.buildInOperations.DivisionOpFactory;
import com.dxc.cherry.classes.parser.buildInOperations.MultiplicationOpFactory;
import com.dxc.cherry.classes.parser.buildInOperations.SubtractionOpFactory;
import com.dxc.cherry.exceptions.InvalidExpressionException;
import com.dxc.cherry.exceptions.InvalidOperationException;

class CalculatorTest {
	

	@Test void ExampleExpressionShouldThrowInvalidOperationException() {
	  Calculator calc = Calculator.builder.include(new AdditionOpFactory(), 0)
								  .include(new MultiplicationOpFactory(), 1)
								  .include(new SubtractionOpFactory(), 1)
								  .include(new DivisionOpFactory(), 0).build();
	  String exampleExpression = "3+5*(7-1)/(7#8)"; 
	  assertThrows(InvalidOperationException.class, () ->
	  {calc.calculate(exampleExpression);}); 
	}

	@Test
	void InvalidExpressionExceptionMissingBracketTest() {
		Calculator calc = Calculator.builder.include(new AdditionOpFactory(), 0)
				  .include(new MultiplicationOpFactory(), 1)
				  .include(new SubtractionOpFactory(), 1)
				  .include(new DivisionOpFactory(), 0).build();
		String exampleExpression = "3+5)*(7-1)/(7+8)";
		assertThrows(InvalidExpressionException.class, () -> { calc.calculate(exampleExpression); });
	}

	@Test
	void InvalidExpressionExceptionMissingOperandTest() {
		Calculator calc = Calculator.builder.include(new AdditionOpFactory(), 0)
				  .include(new MultiplicationOpFactory(), 1)
				  .include(new SubtractionOpFactory(), 1)
				  .include(new DivisionOpFactory(), 0).build();
		String exampleExpression = "3+5*(7-1)/(7+)";
		assertThrows(InvalidExpressionException.class, () -> {calc.calculate(exampleExpression); });
	}

	@Test
	void CorrectCalculatorTest() throws Exception {
		Calculator calc = Calculator.builder.include(new AdditionOpFactory(), 0).include(new MultiplicationOpFactory(), 1).build();
		assertEquals(12, calc.calculate("(2+2)*3"));
	}
	
	@Test
	void ReturnResultTest() throws Exception {
		Calculator calc = Calculator.builder.include(new AdditionOpFactory(), 0)
				  .include(new MultiplicationOpFactory(), 1)
				  .include(new SubtractionOpFactory(), 1)
				  .include(new DivisionOpFactory(), 0).build();		
		calc.calculate("3+5*(7-1)/(7+8)");
		assertEquals(5, calc.returnResult());
	}
	
	@Test
	void TestDivideByZero() throws Exception {
		Calculator calc = Calculator.builder.include(new AdditionOpFactory(), 0)
				  .include(new MultiplicationOpFactory(), 1)
				  .include(new SubtractionOpFactory(), 1)
				  .include(new DivisionOpFactory(), 0).build();
		assertThrows(ArithmeticException.class, () -> {calc.calculate("3+5/(7-7)*(8-7)");});
	}
}
