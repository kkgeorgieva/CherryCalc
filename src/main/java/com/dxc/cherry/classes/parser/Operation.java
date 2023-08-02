package com.dxc.cherry.classes.parser;

public interface Operation {
	/**
	 * Evaluates the arithmetic expression represented by the ASTNode and its sub-trees.
	 * @return The result of the evaluated arithmetic expression as a floating-point number.
	 */
	public float eval();
}
