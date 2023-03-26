package com.example.inspire_app.models;

public class GetLiked {
    private String _id;
    private String user;
    private PostID postid;
    private int __v;

    public GetLiked(String _id, String user, PostID postid, int __v) {
        this._id = _id;
        this.user = user;
        this.postid = postid;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public PostID getPostid() {
        return postid;
    }

    public void setPostid(PostID postid) {
        this.postid = postid;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
