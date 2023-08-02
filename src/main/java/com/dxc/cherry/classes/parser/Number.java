package com.dxc.cherry.classes.parser;

import com.dxc.cherry.classes.parser.buildInOperations.ASTNode;

public class Number extends ASTNode {
	/**
     * Constructs a leaf node with the given value.
     *
     * @param value The value represents the .
     */
	
	public Number(Float value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	/**
	 *Returns the value of the node.
	 */
	@Override
	public float eval() {
		return value;
	}
}

