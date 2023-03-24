package com.example.inspire_app.responsemodels;

import com.example.inspire_app.models.GetLiked;

import java.util.List;

public class GetLikedResponse {
    private String message;
    private List<GetLiked> data;

    public GetLikedResponse(String message, List<GetLiked> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GetLiked> getData() {
        return data;
    }

    public void setData(List<GetLiked> data) {
        this.data = data;
    }
}
