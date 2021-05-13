package com.example.calculator.nodes;

public class NodeMultiply extends NodeBin {
    public NodeMultiply(Node left, Node right) {
        super(left, right);
    }

    @Override
    protected double bin(double left, double right) {
        return left * right;
    }
}
