package com.moringaschool.mywallpaperapi;

public class WallpaperModel {
    private String title, likes, imageurl;

    public WallpaperModel(String title, String likes, String imageurl) {
        this.title = title;
        this.likes = likes;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
