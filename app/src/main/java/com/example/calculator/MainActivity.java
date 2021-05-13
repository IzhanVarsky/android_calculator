package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.nodes.ParseException;

public class MainActivity extends AppCompatActivity {

    private final String TEXT = "TEXT";
    private final String MEMORY = "MEMORY";
    private TextView textView;
    private Button[] calc;
    private Button[] digits;
    private Button[] operations;
    private double mem;

    private String getText() {
        return textView.getText().toString();
    }

    private void initialize() {
        textView = findViewById(R.id.text);
        calc = new Button[]{
                findViewById(R.id.butClear),
                findViewById(R.id.butDelete)
        };
        digits = new Button[]{
                findViewById(R.id.but0),
                findViewById(R.id.but1),
                findViewById(R.id.but2),
                findViewById(R.id.but3),
                findViewById(R.id.but4),
                findViewById(R.id.but5),
                findViewById(R.id.but6),
                findViewById(R.id.but7),
                findViewById(R.id.but8),
                findViewById(R.id.but9)
        };
        operations = new Button[]{
                findViewById(R.id.butEq),
                findViewById(R.id.butDecPoint),
                findViewById(R.id.butPercent),
                findViewById(R.id.butSign),
                findViewById(R.id.butAdd),
                findViewById(R.id.butSub),
                findViewById(R.id.butMult),
                findViewById(R.id.butDiv),
                findViewById(R.id.butOpBracket),
                findViewById(R.id.butClBracket),
                findViewById(R.id.butSin),
                findViewById(R.id.butCos)
        };
        mem = 0.0;
    }

    private void setGlobal() {
        setCalc();
        setOperations();
        setDigits();
    }

    private void setOperations() {
        operations[0].setOnClickListener(v -> {
//            operations[1].setVisibility(View.INVISIBLE);
            Parser p = new Parser();
            try {
                double temp = p.parse(getText()).evaluate();
                String resStr = String.valueOf(temp);
                if (resStr.endsWith(".0")) resStr = resStr.substring(0, resStr.lastIndexOf(".0"));
                textView.setText(resStr);
            } catch (ParseException e) {
                textView.setText(e.getMessage());
            }
        });
        operations[1].setOnClickListener(v -> {
            String temp = getText() + ".";
            textView.setText(temp);
        });
        operations[2].setOnClickListener(v -> {
            String temp = getText() + "%";
            textView.setText(temp);
        });
        operations[3].setOnClickListener(v -> {
            String temp = "-(" + getText() + ")";
            textView.setText(temp);
        });
        operations[4].setOnClickListener(v -> {
            String temp = getText() + "+";
            textView.setText(temp);
        });
        operations[5].setOnClickListener(v -> {
            String temp = getText() + "-";
            textView.setText(temp);
        });
        operations[6].setOnClickListener(v -> {
            String temp = getText() + "*";
            textView.setText(temp);
        });
        operations[7].setOnClickListener(v -> {
            String temp = getText() + "/";
            textView.setText(temp);
        });
        operations[8].setOnClickListener(v -> {
            if (getText().equals("0")) {
                textView.setText("(");
                return;
            }
            String temp = getText() + "(";
            textView.setText(temp);
        });
        operations[9].setOnClickListener(v -> {
            String temp = getText() + ")";
            textView.setText(temp);
        });
        operations[10].setOnClickListener(v -> {
            if (getText().equals("0")) {
                textView.setText("sin(");
                return;
            }
            String temp = getText() + "sin(";
            textView.setText(temp);
        });
        operations[11].setOnClickListener(v -> {
            if (getText().equals("0")) {
                textView.setText("cos(");
                return;
            }
            String temp = getText() + "cos(";
            textView.setText(temp);
        });
    }

    private void setDigits() {
        for (int i = 0; i < digits.length; i++) {
            final String finalI = String.valueOf(i);
            digits[i].setOnClickListener(v -> {
                if (getText().equals("0")) {
                    textView.setText(finalI);
                    return;
                }
                String temp = getText() + finalI;
                textView.setText(temp);
            });
        }
    }

    private void setCalc() {
        calc[0].setOnClickListener(v -> textView.setText("0"));
        calc[1].setOnClickListener(v -> {
            String temp = getText();
            if (temp.equals("0")) {
                return;
            }
            if (temp.length() == 1) {
                textView.setText("0");
                return;
            }
            temp = temp.substring(0, temp.length() - 1);
            textView.setText(temp);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setGlobal();

        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(TEXT));
            mem += savedInstanceState.getDouble(MEMORY);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(TEXT, getText());
        outState.putDouble(MEMORY, mem);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString(TEXT));
        mem += savedInstanceState.getDouble(MEMORY);
    }
}
