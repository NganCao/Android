package com.example.quanlysanpham;

import java.util.ArrayList;

public class Menu extends Item {

    private ArrayList<Product> listSP = null;

    public Menu (String ma, String ten){
        super(ma,ten);
        this.listSP = new ArrayList<Product>();
    }

    public boolean isDuplicate(Product p)
    {
        for(Product p1: listSP)
        {
            if(p1.getId().trim().equalsIgnoreCase(p.getId().trim()))
                return true;
        }
        return false;
    }

    public boolean addProduct (Product p){
        boolean isDup = isDuplicate(p);
        if (!isDup){
            p.setMenu(this);
            return listSP.add(p);
        }
        return !isDup;
    }

    public ArrayList<Product> getListSP (){
        return this.listSP;
    }

    public int size(){
        return listSP.size();
    }

    public Product get(int i){
        return listSP.get(i);
    }

}
