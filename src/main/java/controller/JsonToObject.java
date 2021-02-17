package controller;

import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class JsonToObject {


    //Product product = new Product();


    private static void parseJsonEx() {

        try {

            // Product p = new Product();
            JsonParser parser = new JsonParser();
            JsonArray a = (JsonArray) parser.parse(new FileReader("files/coopSort.json"));

            for (Object o : a) {
                JsonObject p = (JsonObject) o;

                String cat0 = "Dryck\n                    \n                        \n                            \n                                Pil";
                String cat1 = "Vegetariskt\n                    \n                        \n                            \n                                Pil";
                String cat2 = "Skafferi\n                    \n                        \n                            \n                                Pil";
                String cat3 = "Kött, Fågel & Chark\n                    \n                        \n                            \n                                Pil";
                String cat4 = "Frys\n                    \n                        \n                            \n                                Pil";
                String cat5 = "Fisk & Skaldjur\n                    \n                        \n                            \n                                Pil";
                String cat6 = "Kryddor & Smaksättare\n                    \n                        \n                            \n                                Pil";
                String cat7 = "Mejeri & Ägg\n                    \n                        \n                            \n                                Pil";
                String cat8 = "\"Bröd & Bageri\\n                    \\n                        \\n                            \\n                                Pil\"";
                String cat9 = "Ost\n                    \n                        \n                            \n                                Pil";
                String cat10 = "Frukt & Grönsaker\n                    \n                        \n                            \n                                Pil";

                String prod_name = String.valueOf(p.get("prod_name").getAsString());
                String prod_price = String.valueOf(p.get("prod_price").getAsString());
                String categori = String.valueOf(p.get("Category").getAsString());




                if (categori.equals(cat0)) {
                    System.out.println("dryck ");
                System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat1)) {
                    System.out.println("vegetariskt ");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat2)) {
                    System.out.println("skafferi ");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat3)) {
                    System.out.println("Kött, Fågel & Chark ");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat4)) {
                    System.out.println("Frys ");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat5)) {
                    System.out.println("Fisk & Skaldjur ");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat6)) {
                    System.out.println("Kryddor & Smaksättare");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat7)) {
                    System.out.println("Mejeri & Ägg");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat8)) {
                    System.out.println("Bröd & Bageri ");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat9)) {
                    System.out.println("Ost");
                    System.out.println(prod_name + " " + prod_price);
                }
                if (categori.equals(cat10)) {
                    System.out.println("Frukt & Grönsaker");
                    System.out.println(prod_name + " " + prod_price);
                }

            }
        } catch (IOException e) {
        e.printStackTrace();
          }


    }

        public static void main (String[]args){

        parseJsonEx();





        }

    }

