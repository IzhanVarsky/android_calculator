package com.example.calculator;

import com.example.calculator.nodes.*;

public class Parser {

    private Tokenizer tokenizer;

    private Node unary() throws ParseException {
        Node result;
        switch (tokenizer.getNextToken()) {
            case CONSTANT:
                result = new NodeConstant(tokenizer.getValue());
                tokenizer.getNextToken();
                break;
            case NEGATE:
                result = new NodeNegation(unary());
                break;
            case OPENING_BRACKET:
                result = firstHyperOperator();
                if (tokenizer.getToken() != Token.CLOSING_BRACKET) {
                    throw new ParseException("ERROR");
                }
                tokenizer.getNextToken();
                break;
            case SIN:
                if (tokenizer.getNextToken() != Token.OPENING_BRACKET)
                    throw new ParseException("No opening braces after 'sin'");
                result = new NodeSin(firstHyperOperator());
                if (tokenizer.getToken() != Token.CLOSING_BRACKET)
                    throw new ParseException("No closing braces after 'sin'");
                tokenizer.getNextToken();
                break;
            case COS:
                if (tokenizer.getNextToken() != Token.OPENING_BRACKET)
                    throw new ParseException("No opening braces after 'cos'");
                result = new NodeCos(firstHyperOperator());
                if (tokenizer.getToken() != Token.CLOSING_BRACKET)
                    throw new ParseException("No closing braces after 'cos'");
                tokenizer.getNextToken();
                break;
            default:
                throw new ParseException("ERROR");
        }
        if (tokenizer.getForPercent()) {
            result = new NodePercent(new NodeConstant(tokenizer.getValue()));
        }
        return result;
    }

    private Node secondHyperOperator() throws ParseException {
        Node result = unary();
        do {
            switch (tokenizer.getToken()) {
                case MULTIPLY:
                    result = new NodeMultiply(result, unary());
                    break;
                case DIVIDE:
                    result = new NodeDivide(result, unary());
                    break;
                default:
                    return result;
            }
        } while (true);
    }

    private Node firstHyperOperator() throws ParseException {
        Node result = secondHyperOperator();
        do {
            switch (tokenizer.getToken()) {
                case ADD:
                    result = new NodeAdd(result, secondHyperOperator());
                    break;
                case SUBTRACT:
                    result = new NodeSubtract(result, secondHyperOperator());
                    break;
                default:
                    return result;
            }
        } while (true);
    }

    public Node parse(String expression) throws ParseException {
        tokenizer = new Tokenizer(expression);
        return firstHyperOperator();
    }
}
