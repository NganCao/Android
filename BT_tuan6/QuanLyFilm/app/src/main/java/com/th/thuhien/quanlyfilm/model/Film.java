package com.th.thuhien.quanlyfilm.model;

public class Film {
    private String img;
    private String tenTA;
    private String tenTV;
    private int rating;

    public Film() {
    }

    public Film(String img, String tenTA, String tenTV, int rating) {
        this.img = img;
        this.tenTA = tenTA;
        this.tenTV = tenTV;
        this.rating = rating;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTenTA() {
        return tenTA;
    }

    public void setTenTA(String tenTA) {
        this.tenTA = tenTA;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
