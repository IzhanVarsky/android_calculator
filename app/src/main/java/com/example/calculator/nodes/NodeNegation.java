package com.example.calculator.nodes;

public class NodeNegation extends NodeUn {
    public NodeNegation(Node central) {
        super(central);
    }

    @Override
    protected double un(double central) {
        return -central;
    }
}
