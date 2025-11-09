package com.example.aplikasipelaporaninsiden;

public class Edukasi {
    private int thumbnail;
    private String title;
    private String views;

    public Edukasi(int thumbnail, String title, String views) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.views = views;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getViews() {
        return views;
    }
}
