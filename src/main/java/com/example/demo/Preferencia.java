package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;

public class Preferencia {
    private ArrayList<Item> items;
    private Payer payer;
    private HashMap<String,String> backUrls;
    private String autoReturn;
    private PaymentMethods paymentMethods;
    private String notificationUrl;
    private String statementDescriptor;
    private String externalReference;
    private boolean expires;
    private String expirationDateFrom;
    private String expirationDateTo;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public HashMap<String, String> getBackUrls() {
        return backUrls;
    }

    public void setBackUrls(HashMap<String, String> backUrls) {
        this.backUrls = backUrls;
    }

    public String getAutoReturn() {
        return autoReturn;
    }

    public void setAutoReturn(String autoReturn) {
        this.autoReturn = autoReturn;
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public boolean isExpires() {
        return expires;
    }

    public void setExpires(boolean expires) {
        this.expires = expires;
    }

    public String getExpirationDateFrom() {
        return expirationDateFrom;
    }

    public void setExpirationDateFrom(String expirationDateFrom) {
        this.expirationDateFrom = expirationDateFrom;
    }

    public String getExpirationDateTo() {
        return expirationDateTo;
    }

    public void setExpirationDateTo(String expirationDateTo) {
        this.expirationDateTo = expirationDateTo;
    }
}
