package com.example.calculator.nodes;

public class NodeCos extends NodeUn {
    public NodeCos(Node central) {
        super(central);
    }

    @Override
    protected double un(double central) {
        return Math.cos(central);
    }
}
