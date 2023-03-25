package com.example.inspire_app.responsemodels;

import com.example.inspire_app.models.DetailsModel;
import com.example.inspire_app.models.PostData;

import java.util.List;

public class GetDetailsResponse {
    private String message;
    private List<DetailsModel> data;

    public GetDetailsResponse(String message, List<DetailsModel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DetailsModel> getData() {
        return data;
    }

    public void setData(List<DetailsModel> data) {
        this.data = data;
    }
}
