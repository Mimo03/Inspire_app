package com.example.inspire_app.responsemodels;

import com.example.inspire_app.models.LoginData;

import java.util.List;

public class LoginResponse {
    private String message;
    private List<LoginData> data;
    private String token;
    private Boolean status;

    public LoginResponse(String message, List<LoginData> data, String token, Boolean status) {
        this.message = message;
        this.data = data;
        this.token = token;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LoginData> getData() {
        return data;
    }

    public void setData(List<LoginData> data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
