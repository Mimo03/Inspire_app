package com.example.inspire_app.models;

public class PostID {
    private String _id;
    private String category;
    private String organization;
    private String imageurl;

    public PostID(String _id, String category, String organization, String imageurl) {
        this._id = _id;
        this.category = category;
        this.organization = organization;
        this.imageurl = imageurl;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
}
