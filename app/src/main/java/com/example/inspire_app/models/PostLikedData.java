package com.example.inspire_app.models;

public class PostLikedData {
    private String user;
    private String postid;

    public PostLikedData(String user, String postid) {
        this.user = user;
        this.postid = postid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }
}
