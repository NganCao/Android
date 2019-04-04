package com.example.quanlybaihat;

public class socialNetwork {

    public int icon;
    public String tittle;
    public String singer;
    public String timeplay;

    public socialNetwork() {
    }

    public socialNetwork(int icon, String tittle, String singer, String timeplay) {
        this.icon = icon;
        this.tittle = tittle;
        this.singer = singer;
        this.timeplay = timeplay;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTimeplay() {
        return timeplay;
    }

    public void setTimeplay(String timeplay) {
        this.timeplay = timeplay;
    }

    @Override
    public String toString() {
        return "socialNetwork{" +
                "icon=" + icon +
                ", tittle='" + tittle + '\'' +
                ", singer='" + singer + '\'' +
                ", timeplay='" + timeplay + '\'' +
                '}';
    }
}
