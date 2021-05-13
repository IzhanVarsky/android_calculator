package com.example.calculator.nodes;

public class NodeConstant implements Node {

    private double number;

    public NodeConstant(double number) {
        this.number = number;
    }

    @Override
    public double evaluate() {
        return number;
    }
}
