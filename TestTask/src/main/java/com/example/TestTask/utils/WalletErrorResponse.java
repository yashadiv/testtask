package com.example.TestTask.utils;

public class WalletErrorResponse {
    private String message;

    public WalletErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
