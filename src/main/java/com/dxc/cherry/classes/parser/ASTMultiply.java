package com.dxc.cherry.classes.parser;


public class ASTMultiply extends ASTNode{
	/**
	 * The "ASTMultiply"  class takes in a left and right operand. 
	 * @param left Represents the left operand of the mathematical expression. 
	 * @param right Represents the right operand of the mathematical expression.
	 */
	public ASTMultiply(ASTNode left, ASTNode right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	/**
	 * The "eval()"method  computes the multiplication of the left and right nodes.
	 * @return The method returns the multiplication of the two elements.
	 */
	public float eval() {
		value = left.eval() * right.eval();
		return value;
	}
	@Override
	public ASTNode createInstance(ASTNode left, ASTNode right) {
		// TODO Auto-generated method stub
		return new ASTMultiply(left, right);
	}
}
