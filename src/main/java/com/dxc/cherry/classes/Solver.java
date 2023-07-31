package com.dxc.cherry.classes;

public class Solver {
	
	//JavaDoc
	public static float sum(float lhs, float rhs) {
		return lhs + rhs;
	}
	//JavaDoc
	public static float substract(float lhs, float rhs) {
		return lhs - rhs;
	}
	//JavaDoc
	public static float divide(float lhs, float rhs) {
		if (rhs == 0.0f) {
			throw new ArithmeticException("/ by zero");
		}
		return lhs / rhs;
	}
	//JavaDoc
	public static float multiply(float lhs, float rhs) {
		return lhs * rhs;
	}

}
