package com.example.calculator.nodes;

public abstract class NodeBin implements Node {

    private Node left, right;

    protected abstract double bin(double left, double right) throws ParseException;

    public NodeBin(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public double evaluate() throws ParseException {
        return bin(left.evaluate(), right.evaluate());
    }
}
