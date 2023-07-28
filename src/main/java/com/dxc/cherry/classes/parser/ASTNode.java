package com.dxc.cherry.classes.parser;

public class ASTNode {
    public String value;
    public ASTNode left;
    public ASTNode right;

    public ASTNode(String value) {
        this.value = value;
    }

    public ASTNode(String value, ASTNode left, ASTNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        if (left == null && right == null) {
            return value;
        } else {
            return "(" + left.toString() + " " + value + " " + right.toString() + ")";
        }
    }
}
