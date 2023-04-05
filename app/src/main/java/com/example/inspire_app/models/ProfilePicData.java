package com.example.inspire_app.models;

public class ProfilePicData {
    private String imageurl;
    private String user;

    public ProfilePicData(String imageurl, String user) {
        this.imageurl = imageurl;
        this.user = user;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
