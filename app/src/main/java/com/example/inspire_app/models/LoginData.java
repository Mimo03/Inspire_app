package com.example.inspire_app.models;

public class LoginData {
    private String _id;
    private String name;
    private String moodleID;
    private String password;
    private int __v;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoodleID() {
        return moodleID;
    }

    public void setMoodleID(String moodleID) {
        this.moodleID = moodleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public LoginData(String _id, String name, String moodleID, String password, int __v) {
        this._id = _id;
        this.name = name;
        this.moodleID = moodleID;
        this.password = password;
        this.__v = __v;
    }
}
