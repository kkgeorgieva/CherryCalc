package com.dxc.cherry.classes.parser;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * The class ASTNode represents a node in the abstract syntax tree, used to evaluate the expression.
 */
public abstract class ASTNode {
	protected Float value;
	protected ASTNode left;
	protected ASTNode right;
	private final ArrayList<String> operationsList = new ArrayList<String>(Arrays.asList("+", "-", "/", "*"));

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
	public ASTNode(ASTNode left, ASTNode right) {
		this.value = null;
		this.left = left;
		this.right = right;
	}


	/*
	 * Evaluates the arithmetic expression represented by the ASTNode and its sub-trees.
	 * @return The result of the evaluated arithmetic expression as a floating-point number.
	 */
	public abstract float eval();
}
