package com.ktao1.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.ktao1.calculator.databinding.ActivityMainBinding;
import com.ktao1.calculator.viewModel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // variables to show userinputs
    private EditText result;
    private EditText newNumber;
    private TextView operation;

    // variable to hold operands and type of calculations;
    private Double operand1 = null;
    private String pendingOperation = "=";

    private MainActivityViewModel mainActivityViewModel = null;

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);



        if(mainActivityViewModel == null){
            mainActivityViewModel = new MainActivityViewModel();
        }

        binding.setViewModel(mainActivityViewModel);


        //setContentView(R.layout.activity_main);


        result = binding.result;
        newNumber = binding.newNumber;
        operation = binding.operation;

        ArrayList<Button> numBtns = new ArrayList<>();
        ArrayList<Button> opBtns = new ArrayList<>();


        Button button0 = binding.button0;
        Button button1 = binding.button1;
        Button button2 = binding.button2;
        Button button3 = binding.button3;
        Button button4 = binding.button4;
        Button button5 = binding.button5;
        Button button6 = binding.button6;
        Button button7 = binding.button7;
        Button button8 = binding.button8;
        Button button9 = binding.button9;
        Button buttonDot = binding.buttonDot;

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


        Button buttonEqule = binding.buttonEqule;
        Button buttonDivide = binding.buttonDivide;
        Button buttonMultiply = binding.buttonMultiply;
        Button buttonMinus = binding.buttonMinus;
        Button buttonPlus = binding.buttonPlus;

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

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
//        if(operand1 != null){
//            outState.putDouble(STATE_OPERAND1, operand1);
//        }
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
//        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
//        operation.setText(pendingOperation);
//    }
}