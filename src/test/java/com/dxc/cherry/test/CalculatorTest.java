package com.dxc.cherry.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dxc.cherry.exceptions.InvalidExpressionException;
import com.dxc.cherry.exceptions.InvalidOperationException;

class CalculatorTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	@Test
	void SumOf2And2ShouldReturn4() {
		assertEquals(4, Sum::calculate(2, 2));
	}
	@Test
	void SubOf2And2ShouldReturn0() {
		assertEquals(0, Sub::calculate(2, 2));
	}
	@Test
	void MultOf2And2ShouldReturn4() {
		assertEquals(4, Mult::calculate(2, 2));
	}
	@Test 
	void DivOf2And2ShouldReturn1(){
		assertEquals(1, Div::calculate(2, 2));
	}
	@Test
	void ExampleExpressionShouldEqual5() {
		String exampleExpression = "3+5*(7-1)/(7+8)";
		Calculator calc = new Calculator();
		assertEquals(5, calc.Calculate(exampleExpression));
	}
	
	@Test
	void DivOf1And0ShouldThrowArithemticException() {
		assertThrows(ArithmeticException, Div::calculate(1,0));
	}
	@Test
	void ExampleExpressionShouldThrowInvalidOperationException() {
		//given that # is not an implemented operation
		String exampleExpression = "3+5*(7-1)/(7#8)";
		Calculator calc = new Calculator();
		assertThrows(InvalidOperationException.class, calc.Calculate(exampleExpression));
	}
	@Test
	void ExampleExpressionShouldThrowInvalidExpressionException() {
		//invalid bracket placement
		String exampleExpression = "3+5)*(7-1)/(7+8)";
		Calculator calc = new Calculator();
		assertThrows(InvalidExpressionException.class, calc.Calculate(exampleExpression));
	}
	@Test
	void ExampleExpressionShouldThrowInvalidExpressionException() {
		//missing left or right operand
		String exampleExpression = "3+5*(7-1)/(7+)";
		Calculator calc = new Calculator();
		assertThrows(InvalidExpressionException.class, calc.Calculate(exampleExpression));
	}
}
