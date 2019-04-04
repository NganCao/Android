package com.example.quanlythucpham;

public class socialNetwork {

    public int icon;
    public String tittle;
    public String status;

    public socialNetwork(int icon, String tittle, String status) {
        this.icon = icon;
        this.tittle = tittle;
        this.status = status;
    }

    public socialNetwork(){

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "socialNetwork{" +
                "icon=" + icon +
                ", tittle='" + tittle + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
