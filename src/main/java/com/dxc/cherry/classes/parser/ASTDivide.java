package com.dxc.cherry.classes.parser;

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
		
		if (right.value == 0) throw new ArithmeticException("\byZero");
		return value;
	}
	
	@Override
	public ASTNode createInstance(ASTNode left, ASTNode right) {
		// TODO Auto-generated method stub
		return new ASTDivide(left, right);
	}
	
	@Override
	public String getSignature() {
		return "/";
	}
}
