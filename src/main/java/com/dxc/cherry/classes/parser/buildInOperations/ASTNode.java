package com.dxc.cherry.classes.parser.buildInOperations;

import com.dxc.cherry.classes.parser.Operation;

/**
 * The class ASTNode represents a node in the abstract syntax tree, used to evaluate the expression.
 */
public abstract class ASTNode implements Operation {
	protected Float value;
	protected Operation left;
	protected Operation right;

	/**
     * Constructs a leaf node with the given value.
     *
     * @param value The value represents the .
     */
	public ASTNode(Float value) {
		this.value = value;
		this.left = null;
		this.right = null;

	}
	
	/**
	 * Constructs a leaf node with the given value and left and right children nodes.
	 * @param value The parameter represents an arithmetic operator
	 * @param left The left child node.
	 * @param right The right child node.
	 */
	public ASTNode(Operation left, Operation right) {
		this.value = null;
		this.left = left;
		this.right = right;
	}
}
