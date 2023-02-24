package com.example.entities.enums;

public enum PaymentMethod {
    CREDIT_CARD {
        @Override public String getDisplayName() { return "Tarjeta de crédito"; }
    },
    DEBIT_CARD {
        @Override public String getDisplayName() { return "Tarjeta de débito"; }
    },
    PAYPAL {
        @Override public String getDisplayName() { return "Paypal"; }
    };

    public abstract String getDisplayName();

}
