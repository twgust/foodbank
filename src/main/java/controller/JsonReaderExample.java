package controller;


import com.google.gson.*;
import entity.Product;

import javax.swing.*;
import java.io.FileReader;
import java.util.ArrayList;

public class JsonReaderExample  {
    JTextArea area = new JTextArea("");
    String prod_name;
    String prod_price;
    String category;


    Product prod;
    public JsonReaderExample() {



    }


    /*private static boolean filterMatch(final String filteredCategory, final Iterable<JsonObject> jsonFilterList) {
        if (jsonFilterList == null) {
            return true;
        }

        try {
            for (JsonObject j : jsonFilterList) {
                if (j.get("Dryck").equals(filteredCategory)) {
                    return false;
                }
                else return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }*/




    private void parseProdObj() {

        try {
            JsonParser parser = new JsonParser();
            JsonArray a = (JsonArray) parser.parse(new FileReader("files/coopSort.json"));

            // List for relevant filter




            for (Object o : a) {
                JsonObject product = (JsonObject) o;
                System.out.println("1");

                JsonPrimitive prim = product.get("Category").getAsJsonPrimitive();
                String primString = prim.toString();

                if (primString.equals("Frys\n                    \n                        \n                            \n                                Pil"))
                {
                    System.out.println(primString);
                }





                /*if (primString.contains("Hem & HushÃ¥ll\n                    \n                        \n                            \n                                Pil")) {
                    System.out.println(primString);


                }*/





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
