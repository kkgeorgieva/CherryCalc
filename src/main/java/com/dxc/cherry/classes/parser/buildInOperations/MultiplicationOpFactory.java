package com.dxc.cherry.classes.parser.buildInOperations;

import com.dxc.cherry.classes.parser.Operation;
import com.dxc.cherry.classes.parser.OperationProvider;

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


	@Override
	public Operation getInstance(Operation left, Operation right) {
		return new Multiplication(left, right);
	}

	@Override
	public String operationSignature() {
		return "*";
	}
	
}
