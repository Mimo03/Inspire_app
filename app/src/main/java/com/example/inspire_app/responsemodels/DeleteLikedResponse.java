package com.example.inspire_app.responsemodels;

public class DeleteLikedResponse {
    private String message;

    public DeleteLikedResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
