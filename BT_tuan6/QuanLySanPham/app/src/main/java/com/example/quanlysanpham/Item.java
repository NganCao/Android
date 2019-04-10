package com.example.quanlysanpham;

/**
 class này để sử dụng lại code
 */
public class Item {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item() {
        super();
    }

    public Item(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.id +
                " - " + this.name;
    }
}