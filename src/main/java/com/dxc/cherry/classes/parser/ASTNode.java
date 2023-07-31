package com.dxc.cherry.classes.parser;

import java.util.ArrayList;
import java.util.Arrays;

import com.dxc.cherry.classes.Solver;



/**
 * The class ASTNode represents a node in the abstract syntax tree, used to evaluate the expression.
 */
public class ASTNode {
	private String value;
	private ASTNode left;
	private ASTNode right;
	private final ArrayList<String> operationsList = new ArrayList<String>(Arrays.asList("+", "-", "/", "*"));

	/**
     * Constructs a leaf node with the given value.
     *
     * @param value The value represents the .
     */
	public ASTNode(String value) {
		this.value = value;

	}
	
	/**
	 * Constructs a leaf node with the given value and left and right children nodes.
	 * @param value The parameter represents an arithmetic operator
	 * @param left The left child node.
	 * @param right The right child node.
	 */
	public ASTNode(String value, ASTNode left, ASTNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	/**
     * Returns the value of the current AST node.
     * @return The value of the AST node as a string.
     */
	public String getValue() {
		return value;
	}

	public String toString() {
		if (left == null && right == null) {
			return value;
		} else {
			return "(" + left.toString() + " " + value + " " + right.toString() + ")";
		}
	}

	/*
	 * Evaluates the arithmetic expression represented by the ASTNode and its sub-trees.
	 * @return The result of the evaluated arithmetic expression as a floating-point number.
	 */
	public float eval() {

		String value = this.getValue();

		if (operationsList.contains(value)) {
			switch (value) {
			case "+":
				return Solver.sum(left.eval(), right.eval());
			case "-":
				return Solver.substract(left.eval(), right.eval());
			case "*":
				return Solver.multiply(left.eval(), right.eval());
			case "/":
				return Solver.divide(left.eval(), right.eval());
			}
		}

		return Float.parseFloat(value);
	}
}
