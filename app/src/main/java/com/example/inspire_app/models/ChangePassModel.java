package com.example.inspire_app.models;

public class ChangePassModel {

    private String _id;
    private String password;

    public ChangePassModel(String _id, String password) {
        this._id = _id;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
