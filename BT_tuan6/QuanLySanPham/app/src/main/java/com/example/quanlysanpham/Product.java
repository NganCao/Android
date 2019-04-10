package com.example.quanlysanpham;

public class Product extends Item {
    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Product() {
        super();
    }

    public Product(String id, String name) {
        super(id, name);
    }
}
