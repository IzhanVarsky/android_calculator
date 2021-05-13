package com.example.calculator;

import com.example.calculator.nodes.ParseException;

import java.util.EnumSet;
import java.util.Set;

public class Tokenizer {

    private boolean forPercent;
    private Token token;
    private int index;
    private double value;
    private String expression;
    private int bracketBalance;
    private static final Set<Token> operations = EnumSet.of(
            Token.ADD, Token.SUBTRACT, Token.MULTIPLY, Token.DIVIDE, Token.NEGATE, Token.PERCENT,
            Token.SIN, Token.COS
    );

    public Tokenizer(String expression) {
        this.expression = expression;
        index = 0;
        token = Token.START;
        bracketBalance = 0;
        forPercent = false;
    }

    private void skipWhiteSpaces() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    public Token getToken() {
        return token;
    }

    public int getIndex() {
        return index;
    }

    public double getValue() {
        return value;
    }

    public String getExpression() {
        return expression;
    }

    public int getBracketBalance() {
        return bracketBalance;
    }

    public boolean getForPercent() {
        return forPercent;
    }

    private void checkPercent() throws ParseException {
        if (token != Token.CONSTANT) {
            throw new ParseException("ERROR");
        }
    }

    private void checkOperand() throws ParseException {
        if (operations.contains(token) || token == Token.START || token == Token.OPENING_BRACKET) {
            throw new ParseException("ERROR");
        }
    }

    private void checkOperation() throws ParseException {
        if (token == Token.CLOSING_BRACKET || token == Token.CONSTANT) {
            throw new ParseException("ERROR");
        }
    }

    private String getNumber() throws ParseException {
        int l = index;
        while (index < expression.length() &&
                (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
            index++;
        }
        int r = index;
        index--;
        return expression.substring(l, r);
    }

    public Token getNextToken() throws ParseException {
        nextToken();
        return token;
    }

    private void nextToken() throws ParseException {
        skipWhiteSpaces();
        if (index >= expression.length()) {
            checkOperand();
            token = Token.END;
            forPercent = false;
            return;
        }
        switch (expression.charAt(index)) {
            case '+':
                checkOperand();
                token = Token.ADD;
                forPercent = false;
                break;
            case '-':
                if (token == Token.CLOSING_BRACKET || token == Token.CONSTANT) {
                    token = Token.SUBTRACT;
                } else {
                    if (index + 1 >= expression.length()) {
                        throw new ParseException("ERROR");
                    }
                    if (Character.isDigit(expression.charAt(index + 1))) {
                        index++;
                        String temp = getNumber();
                        try {
                            value = Double.parseDouble("-" + temp);
                        } catch (NumberFormatException e) {
                            throw new ParseException("ERROR");
                        }
                        token = Token.CONSTANT;
                    } else {
                        token = Token.NEGATE;
                    }
                }
                forPercent = false;
                break;
            case '*':
                checkOperand();
                token = Token.MULTIPLY;
                forPercent = false;
                break;
            case '/':
                checkOperand();
                token = Token.DIVIDE;
                forPercent = false;
                break;
            case '%':
                checkPercent();
                token = Token.PERCENT;
                forPercent = true;
                break;
            case '(':
                checkOperation();
                bracketBalance++;
                token = Token.OPENING_BRACKET;
                forPercent = false;
                break;
            case ')':
                if (operations.contains(token) || token == Token.OPENING_BRACKET) {
                    throw new ParseException("ERROR");
                }
                bracketBalance--;
                if (bracketBalance < 0) {
                    throw new ParseException("ERROR");
                }
                token = Token.CLOSING_BRACKET;
                forPercent = false;
                break;
            case 's':
                if (!expression.substring(index, index + 3).equals("sin")) {
                    throw new ParseException("Unknown operation, started with 's'");
                }
                checkOperation();
                index += 2;
                token = Token.SIN;
                break;
            case 'c':
                if (!expression.substring(index, index + 3).equals("cos")) {
                    throw new ParseException("Unknown operation, started with 'c'");
                }
                checkOperation();
                index += 2;
                token = Token.COS;
                break;
            default:
                if (Character.isDigit(expression.charAt(index))) {
                    checkOperation();
                    String temp = getNumber();
                    try {
                        value = Double.parseDouble(temp);
                    } catch (NumberFormatException e) {
                        throw new ParseException("ERROR");
                    }
                    token = Token.CONSTANT;
                }
                forPercent = false;
        }
        index++;
    }
}