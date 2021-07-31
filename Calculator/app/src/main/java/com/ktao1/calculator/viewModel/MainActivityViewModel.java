package com.ktao1.calculator.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ktao1.calculator.BR;
import com.ktao1.calculator.model.Calculator;


public class MainActivityViewModel extends BaseObservable {
    private Calculator calculator;

    private String saveMessage = "Save data";
    private String loadMessage = "Load data";


    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getPendingOperation(){
        return calculator.getPendingOpreation();
    }
    public void setPendingOperation(String pendingOperation){
        calculator.setPendingOpreation(pendingOperation);
        notifyPropertyChanged(BR.pendingOperation);
    }

    @Bindable
    public Double getOperand1(){
        return calculator.getOperand1();
    }
    public void setOperand1(Double Operand1){
        calculator.setOpreand1(Operand1);
        notifyPropertyChanged(BR.operand1);
    }

}
