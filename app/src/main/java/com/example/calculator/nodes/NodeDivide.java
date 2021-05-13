package com.example.calculator.nodes;

public class NodeDivide extends NodeBin {
    public NodeDivide(Node left, Node right) {
        super(left, right);
    }

    @Override
    protected double bin(double left, double right) throws ParseException {
        if (right == 0) {
            throw new ParseException("ERROR");
        }
        return left / right;
    }
}
