package com.example.calculator.nodes;

public class NodeAdd extends NodeBin {
    public NodeAdd(Node left, Node right) {
        super(left, right);
    }

    @Override
    protected double bin(double left, double right) {
        return left + right;
    }
}
