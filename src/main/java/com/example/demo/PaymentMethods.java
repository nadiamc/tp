package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;

public class PaymentMethods {

    private ArrayList<HashMap<String,String>> excludedPaymentMethods;
    private ArrayList<HashMap<String,String>> excludedPaymentTypes;
    private int installments;

    public ArrayList<HashMap<String, String>> getExcludedPaymentMethods() {
        return excludedPaymentMethods;
    }

    public void setExcludedPaymentMethods(ArrayList<HashMap<String, String>> excludedPaymentMethods) {
        this.excludedPaymentMethods = excludedPaymentMethods;
    }

    public ArrayList<HashMap<String, String>> getExcludedPaymentTypes() {
        return excludedPaymentTypes;
    }

    public void setExcludedPaymentTypes(ArrayList<HashMap<String, String>> excludedPaymentTypes) {
        this.excludedPaymentTypes = excludedPaymentTypes;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }
}
