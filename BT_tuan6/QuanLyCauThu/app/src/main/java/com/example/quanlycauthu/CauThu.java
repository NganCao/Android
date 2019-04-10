package com.example.quanlycauthu;

import android.os.Parcel;

public class CauThu {
    public int icon;
    public String name;
    public String team;
    public String hang;

    public CauThu() {
    }

    public CauThu(int icon, String name, String team, String hang) {
        this.icon = icon;
        this.name = name;
        this.team = team;
        this.hang = hang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "CauThu{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", hang='" + hang + '\'' +
                '}';
    }
}
