package com.example.inspire_app.models;

import java.util.List;

public class MostLikedData {
    private String _id;
    private int count;
    private List<MostData> mostpost;

    public MostLikedData(String _id, int count, List<MostData> mostpost) {
        this._id = _id;
        this.count = count;
        this.mostpost = mostpost;
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

    public List<MostData> getMostpost() {
        return mostpost;
    }

    public void setMostpost(List<MostData> mostpost) {
        this.mostpost = mostpost;
    }
}
