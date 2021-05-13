package com.example.calculator.nodes;

public class NodeSin extends NodeUn {
    public NodeSin(Node central) {
        super(central);
    }

    @Override
    protected double un(double central) {
        return Math.sin(central);
    }
}
