package com.ktao1.calculator.model;

public class Calculator {
    String PendingOpreation;
    Double Operand1;

    public Calculator(String pendingOpreation, Double operand1) {
        PendingOpreation = pendingOpreation;
        Operand1 = operand1;
    }

    public String getPendingOpreation() {
        return PendingOpreation;
    }

    public void setPendingOpreation(String pendingOpreation) {
        PendingOpreation = pendingOpreation;
    }

    public Double getOperand1() {
        return Operand1;
    }

    public void setOpreand1(Double operand1) {
        Operand1 = operand1;
    }
}
