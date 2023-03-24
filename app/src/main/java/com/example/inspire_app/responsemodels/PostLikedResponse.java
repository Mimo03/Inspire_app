package com.example.inspire_app.responsemodels;

public class PostLikedResponse {
    private String message;

    public PostLikedResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
