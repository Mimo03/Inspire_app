package com.example.inspire_app.models;

public class MostLikedData {
    private String _id;
    private int count;

    public MostLikedData(String _id, int count) {
        this._id = _id;
        this.count = count;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
