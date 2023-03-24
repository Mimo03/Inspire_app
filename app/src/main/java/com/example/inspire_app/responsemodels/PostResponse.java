package com.example.inspire_app.responsemodels;

import com.example.inspire_app.models.PostData;

import java.util.List;

public class PostResponse {
    private String message;
    private List<PostData> data;

    public PostResponse(String message, List<PostData> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PostData> getData() {
        return data;
    }

    public void setData(List<PostData> data) {
        this.data = data;
    }
}
