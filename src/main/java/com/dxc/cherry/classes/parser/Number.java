package com.dxc.cherry.classes.parser;

import com.dxc.cherry.classes.buildInOperations.ASTNode;

public class Number extends ASTNode {
	/**
     * Constructs a leaf node with the given value.
     *@param value The value represents the number stored by this node.
     */
	
	public Number(Float value) {
		super(value);
	}

	/**
	 *Returns the value of the node.
	 */
	@Override
	public float eval() {
		return value;
	}
}

