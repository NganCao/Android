package com.example.quanlybongda;
public class socialNetwork {

    public int icon;
    public String tittle;
    public String status;
    public int nation;

    socialNetwork(){}

    public socialNetwork(int icon, String tittle, String status, int nation) {
        this.icon = icon;
        this.tittle = tittle;
        this.status = status;
        this.nation = nation;
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

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "socialNetwork{" +
                "icon=" + icon +
                ", tittle='" + tittle + '\'' +
                ", status='" + status + '\'' +
                ", nation=" + nation +
                '}';
    }
}

