package controller;

import com.google.gson.*;
import entity.Product;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class JsonReaderExample extends JFrame {
    JTextArea area = new JTextArea("");
    String prod_name;
    String prod_price;
    String category;

    Product prod;
    public JsonReaderExample() {
        setSize(400, 1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(area);

    }


    private void parseProdObj() {

        try {
            JsonParser parser = new JsonParser();
            JsonArray a = (JsonArray) parser.parse(new FileReader("files/coopSort.json"));
            for (Object o : a) {
                JsonObject product = (JsonObject) o;

                prod = new Product(prod_name, prod_price, category);

                prod_name = String.valueOf(product.get("prod_name"));


                prod_price = String.valueOf(product.get("prod_price"));
                prod.setProd_price(prod_price);


                category = String.valueOf(product.get("Category"));
                prod.setCategory(category);



                System.out.println(prod.addProducts(prod_name, prod_price, category));


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {
        JsonReaderExample e = new JsonReaderExample();
        e.parseProdObj();
    }

}
