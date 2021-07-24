package com.ktao1.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // variables to show userinputs
    private EditText result;
    private EditText newNumber;
    private TextView operation;

    // variable to hold operands and type of calculations;
    private Double operand1 = null;
    private String pendingOperation = "=";

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        operation = findViewById(R.id.operation);

        ArrayList<Button> numBtns = new ArrayList<>();
        ArrayList<Button> opBtns = new ArrayList<>();

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);

        numBtns.add(button0);
        numBtns.add(button1);
        numBtns.add(button2);
        numBtns.add(button3);
        numBtns.add(button4);
        numBtns.add(button5);
        numBtns.add(button6);
        numBtns.add(button7);
        numBtns.add(button8);
        numBtns.add(button9);
        numBtns.add(buttonDot);


        Button buttonEqule = findViewById(R.id.buttonEqule);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonPlus);

        opBtns.add(buttonEqule);
        opBtns.add(buttonDivide);
        opBtns.add(buttonMultiply);
        opBtns.add(buttonMinus);
        opBtns.add(buttonPlus);


        View.OnClickListener numListener = view -> newNumber.append(((Button) view).getText().toString());
        numBtns.forEach(btn -> btn.setOnClickListener(numListener));

        View.OnClickListener opListener = view -> {
            String op = ((Button) view).getText().toString();
            String value = newNumber.getText().toString();

            try{
                Double doubleValue = Double.valueOf(value);
                performOperation(doubleValue, op);
            }catch (NumberFormatException e){
                newNumber.setText("");
            }

            pendingOperation = op;
            operation.setText(pendingOperation);
        };
        opBtns.forEach(btn -> btn.setOnClickListener(opListener));

        Button btnNeg = findViewById(R.id.buttonNeg);
        btnNeg.setOnClickListener(view -> {
            String value = newNumber.getText().toString();
            if(value.length() == 0){
                newNumber.setText("-");
            }else{
                try{
                    Double doubleValue = Double.valueOf(value);
                    doubleValue *= -1;
                    newNumber.setText(doubleValue.toString());

                }catch (NumberFormatException e){
                    newNumber.setText("");
                }
            }
        });

        Button btnClear = findViewById(R.id.buttonClear);
        btnClear.setOnClickListener(view -> {
            newNumber.setText("");
            operation.setText("");
            result.setText("");
            operand1 = null;
        });
    }

    private void performOperation(Double value, String op) {
        if (operand1 == null) {
            operand1 = value;
        } else {
            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0)
                        operand1 = 0.0;
                    else
                        operand1 /= value;
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if(operand1 != null){
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        operation.setText(pendingOperation);
    }
}