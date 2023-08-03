package com.dxc.cherry.classes.buildInOperations;

import com.dxc.cherry.interfaces.parser.Operation;
import com.dxc.cherry.interfaces.parser.OperationProvider;

public class MultiplicationOpFactory implements OperationProvider {
	
	/**
	 * The "Multiplication"  class takes in a left and right operand. 
	 * @param left Represents the left operand of the mathematical expression. 
	 * @param right Represents the right operand of the mathematical expression.
	 */
	private class Multiplication extends ASTNode {
		public Multiplication(Operation left, Operation right) {
			super(left, right);
		}
		
		@Override
		public float eval() {
			return left.eval() * right.eval();
		}
	}

	/**
	 * Returns an instance of the operation inside of this factory.
	 * @param left The left hand side of the expression. 
	 * @param right The right hand side of the expression.
	 */
	@Override
	public Operation getInstance(Operation left, Operation right) {
		return new Multiplication(left, right);
	}
	/**
	 * @param return Returns the text representation of the operation inside of this factory.
	 */
	@Override
	public String operationSignature() {
		return "*";
	}
	
}
