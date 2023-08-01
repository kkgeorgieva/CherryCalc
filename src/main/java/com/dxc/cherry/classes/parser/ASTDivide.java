package com.dxc.cherry.classes.parser;

import com.dxc.cherry.classes.Solver;

public class ASTDivide extends ASTNode{

	/**
	 * The "ASTDivide"  class takes in a left and right operand. 
	 * @param left Represents the left operand of the mathematical expression. 
	 * @param right Represents the right operand of the mathematical expression.
	 */
	public ASTDivide(ASTNode left, ASTNode right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	/**
	 * The "eval()"method  computes the division of the left and right nodes.
	 * @return The method returns the division of the two elements.
	 */
	public float eval() {
		value = left.eval()/ right.eval();
		return value;
	}
}
