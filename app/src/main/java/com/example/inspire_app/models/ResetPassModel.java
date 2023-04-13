package com.example.inspire_app.models;

public class ResetPassModel {

    private String moodleID;
    private String password;

    public ResetPassModel(String moodleID, String password) {
        this.moodleID = moodleID;
        this.password = password;
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
}
