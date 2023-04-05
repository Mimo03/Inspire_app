package com.example.inspire_app.responsemodels;

public class CommentResponse {
    private String message;

    public CommentResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
