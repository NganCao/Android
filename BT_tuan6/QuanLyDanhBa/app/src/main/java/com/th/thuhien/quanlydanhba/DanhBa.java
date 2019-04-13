package com.th.thuhien.quanlydanhba;

public class DanhBa {
    private String name;
    private String phone;

    public DanhBa() {
    }

    public DanhBa(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.name + "[" + this.phone + "]";
    }
}
