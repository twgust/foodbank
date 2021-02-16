package entity;

import java.util.ArrayList;

public class Product {
    public String category;
    public String prod_name;
    public String prod_price;

    public Product(String prod_name, String prod_price, String category) {
        this.category = category;
        this.prod_name = prod_name;
        this.prod_price = prod_price;
    }




    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getProd_name() {
        return prod_name;
    }
    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }
    public String getProd_price() {
        return prod_price;
    }
    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }


    /*
    Lägger till Produktnamn , pris och kategori för varje produktobjekt i en arraylist.
     */
    public ArrayList<Product> addProducts(String prod_name, String prod_price, String category) {
        ArrayList<Product> list = new ArrayList<Product>();
        list.add(new Product(prod_name, prod_price, category));

        return list;

    }




}
