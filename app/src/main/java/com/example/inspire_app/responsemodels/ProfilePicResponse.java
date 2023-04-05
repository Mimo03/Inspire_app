package com.example.inspire_app.responsemodels;

public class ProfilePicResponse {
    private String message;

    public ProfilePicResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
