package com.example.inspire_app.models;

public class PostLikedData {
    private String category;
    private String organization;
    private String imageurl;
    private String postid;

    public PostLikedData(String category, String organization, String imageurl, String postid) {
        this.category = category;
        this.organization = organization;
        this.imageurl = imageurl;
        this.postid = postid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }
}
