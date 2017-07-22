package com.vineesh.suthanbathery.model;

/**
 * Created by vineesh on 30/06/2017.
 */

public class Icons {
    private String title;
    private int background;
    private int cover_img;

    public Icons(String title, int cover_img) {
        this.title = title;
        this.cover_img = cover_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getCover_img() {
        return cover_img;
    }

    public void setCover_img(int cover_img) {
        this.cover_img = cover_img;
    }
}
