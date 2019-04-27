package com.example.bt1;

public class ColorObject {
    private String color, value;

    public ColorObject() {
    }

    public ColorObject(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ColorObject{" +
                "color='" + color + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
