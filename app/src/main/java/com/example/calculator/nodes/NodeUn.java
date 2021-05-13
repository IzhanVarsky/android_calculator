package com.example.calculator.nodes;

public abstract class NodeUn implements Node {

    private Node central;
    protected abstract double un(double central);

    public NodeUn(Node central) {
        this.central = central;
    }

    public double evaluate() throws ParseException {
        return un(central.evaluate());
    }
}
