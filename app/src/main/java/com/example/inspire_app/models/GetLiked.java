package com.example.inspire_app.models;

public class GetLiked {
    private String _id;
    private String category;
    private String organization;
    private String imageurl;
    private String postid;
    private int __v;

    public GetLiked(String _id, String category, String organization, String imageurl, String postid, int __v) {
        this._id = _id;
        this.category = category;
        this.organization = organization;
        this.imageurl = imageurl;
        this.postid = postid;
        this.__v = __v;
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

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
