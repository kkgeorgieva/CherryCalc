package com.dxc.cherry.classes;

/**
 * The Solver class provides static methods for performing basic arithmetic operations.
 */
public class Solver {
	
	/**
	 * The "sum"  method takes in two operands of type float and computes their sum.
	 * @param lhs Represents the left operand of the mathematical expression. 
	 * @param rhs Represents the right operand of the mathematical expression.
	 * @return The method returns the sum of the two elements.
	 */
	public static float sum(float lhs, float rhs) {
		return lhs + rhs;
	}
	/**
	 * The "subtract"  method takes in two operands of type float and computes their difference.
	 * @param lhs Represents the left operand of the mathematical expression. 
	 * @param rhs Represents the right operand of the mathematical expression.
	 * @return The method returns the difference of the two elements.
	 */
	public static float substract(float lhs, float rhs) {
		return lhs - rhs;
	}
	/**
	 * The "divide"  method takes in two operands of type float and computes the result of their division.
	 * @param lhs Represents the left operand of the mathematical expression. 
	 * @param rhs Represents the right operand of the mathematical expression.
	 * @return The method returns the result of the division of the two elements.
	 */
	public static float divide(float lhs, float rhs) {
		if (rhs == 0.0f) {
			throw new ArithmeticException("/ by zero");
		}
		return lhs / rhs;
	}
	/**
	 * The "multiply"  method takes in two operands of type float and computes the result of their multiplication.
	 * @param lhs Represents the left operand of the mathematical expression. 
	 * @param rhs Represents the right operand of the mathematical expression.
	 * @return The method returns the result of the multiplication of the two elements.
	 */
	public static float multiply(float lhs, float rhs) {
		return lhs * rhs;
	}

}
