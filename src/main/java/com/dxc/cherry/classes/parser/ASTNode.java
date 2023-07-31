package com.dxc.cherry.classes.parser;

import java.util.ArrayList;
import java.util.Arrays;

import com.dxc.cherry.classes.Solver;

//JavaDoc
public class ASTNode {
	private String value;
	private ASTNode left;
	private ASTNode right;
	private final ArrayList<String> operationsList = new ArrayList<String>(Arrays.asList("+", "-", "/", "*"));

	public ASTNode(String value) {
		this.value = value;

	}

	// JavaDoc
	public ASTNode(String value, ASTNode left, ASTNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

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

	// JavaDoc
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
