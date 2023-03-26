package com.example.inspire_app.responsemodels;

import com.example.inspire_app.models.GetLiked;

import java.util.List;

public class GetLikedResponse {
    private List<GetLiked> data;

    public GetLikedResponse(List<GetLiked> data) {
        this.data = data;
    }



    public List<GetLiked> getData() {
        return data;
    }

    public void setData(List<GetLiked> data) {
        this.data = data;
    }
}
