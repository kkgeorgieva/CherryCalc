package com.dxc.cherry.classes.parser.buildInOperations;

import com.dxc.cherry.classes.parser.Operation;
import com.dxc.cherry.classes.parser.OperationProvider;

public class DivisionOpFactory implements OperationProvider {
	
	/**
	 * The "Division" class takes in a left and right operand. 
	 * @param left Represents the left operand of the mathematical expression. 
	 * @param right Represents the right operand of the mathematical expression.
	 */
	private class Division extends ASTNode {
		public Division(Operation left, Operation right) {
			super(left, right);
		}
		
		@Override
		public float eval() {
			float x = left.eval();
			float y = right.eval();
			if (y == 0.0f) throw new ArithmeticException("\byZero");
			return x/y;
		}
	}


	@Override
	public Operation getInstance(Operation left, Operation right) {
		return new Division(left, right);
	}

	@Override
	public String operationSignature() {
		return "/";
	}
	
}
