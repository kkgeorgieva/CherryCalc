package com.dxc.cherry.classes.parser;


public class ASTSum extends ASTNode{

	
	
	/**
	 * The "ASTSum"  class takes in a left and right operand. 
	 * @param left Represents the left operand of the mathematical expression. 
	 * @param right Represents the right operand of the mathematical expression.
	 */
	public ASTSum(ASTNode left, ASTNode right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	/**
	 * The "eval()"method  computes the sum of the left and right nodes.
	 * @return The method returns the sum of the two elements.
	 */
	public float eval() {
		value = left.eval() + right.eval();
		return value;
	}
}
