package com.example.calculator;

import com.example.calculator.nodes.ParseException;

public class Tester {
    public static void main(String[] args) {
        Parser p = new Parser();
        try {
            System.out.println(p.parse("100+123").evaluate());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}
