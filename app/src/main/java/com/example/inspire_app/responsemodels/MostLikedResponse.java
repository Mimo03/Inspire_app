package com.example.inspire_app.responsemodels;

import com.example.inspire_app.models.MostLikedData;

import java.util.List;

public class MostLikedResponse {
    private List<MostLikedData> data;

    public MostLikedResponse(List<MostLikedData> data) {
        this.data = data;
    }

    public List<MostLikedData> getData() {
        return data;
    }

    public void setData(List<MostLikedData> data) {
        this.data = data;
    }
}
