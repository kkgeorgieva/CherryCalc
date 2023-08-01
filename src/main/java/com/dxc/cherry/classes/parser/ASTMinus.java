package com.dxc.cherry.classes.parser;


public class ASTMinus extends ASTNode{

	/**
	 * The "ASTMinus"  class takes in a left and right operand. 
	 * @param left Represents the left operand of the mathematical expression. 
	 * @param right Represents the right operand of the mathematical expression.
	 */
	public ASTMinus(ASTNode left, ASTNode right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	/**
	 * The "eval()"method  computes the subtraction of the left and right nodes.
	 * @return The method returns the subtraction of the two elements.
	 */
	public float eval() {
		value = left.eval() - right.eval();
		return value;
	}
	@Override
	public ASTNode createInstance(ASTNode left, ASTNode right) {
		// TODO Auto-generated method stub
		return new ASTMinus(left, right);
	}
}
