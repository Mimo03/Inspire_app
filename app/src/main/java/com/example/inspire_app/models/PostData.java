package com.example.inspire_app.models;

import java.util.List;

public class PostData {
    private List<String> allcomment;
    private String _id;
    private String category;
    private String content;
    private String organization;
    private String socialmedia;
    private String imageurl;
    private int __v;
    private Boolean comments;

    public PostData(List<String> allcomment, String _id, String category, String content, String organization, String socialmedia, String imageurl, int __v, Boolean comments) {
        this.allcomment = allcomment;
        this._id = _id;
        this.category = category;
        this.content = content;
        this.organization = organization;
        this.socialmedia = socialmedia;
        this.imageurl = imageurl;
        this.__v = __v;
        this.comments = comments;
    }

    public List<String> getAllcomment() {
        return allcomment;
    }

    public void setAllcomment(List<String> allcomment) {
        this.allcomment = allcomment;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getSocialmedia() {
        return socialmedia;
    }

    public void setSocialmedia(String socialmedia) {
        this.socialmedia = socialmedia;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Boolean getComments() {
        return comments;
    }

    public void setComments(Boolean comments) {
        this.comments = comments;
    }
}
