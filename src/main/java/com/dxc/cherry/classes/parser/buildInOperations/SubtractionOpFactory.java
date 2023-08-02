package com.dxc.cherry.classes.parser.buildInOperations;

import com.dxc.cherry.classes.parser.Operation;
import com.dxc.cherry.classes.parser.OperationProvider;

public class SubtractionOpFactory implements OperationProvider {
	
	/**
	 * The "Subtraction"  class takes in a left and right operand. 
	 * @param left Represents the left operand of the mathematical expression. 
	 * @param right Represents the right operand of the mathematical expression.
	 */
	private class Subtraction extends ASTNode {
		public Subtraction(Operation left, Operation right) {
			super(left, right);
		}
		
		@Override
		public float eval() {
			return left.eval() - right.eval();
		}
	}


	@Override
	public Operation getInstance(Operation left, Operation right) {
		return new Subtraction(left, right);
	}

	@Override
	public String operationSignature() {
		return "-";
	}
	
}
