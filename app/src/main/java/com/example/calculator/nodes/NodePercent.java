package com.example.calculator.nodes;

public class NodePercent extends NodeUn {
    public NodePercent(Node central) {
        super(central);
    }

    @Override
    protected double un(double central) {
        return central / 100.0;
    }
}
