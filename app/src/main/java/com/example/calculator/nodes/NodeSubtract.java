package com.example.calculator.nodes;

public class NodeSubtract extends NodeBin {
    public NodeSubtract(Node left, Node right) {
        super(left, right);
    }

    @Override
    protected double bin(double left, double right) {
        return left - right;
    }
}
