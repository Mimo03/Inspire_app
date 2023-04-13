package com.example.inspire_app.responsemodels;

public class ResetPassResponse {

    private String message;

    public ResetPassResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
