package utils;

import com.google.gson.*;
import entity.Product;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/*
This class handles product data. It reads Json files to create a list of products which can be sent to the database.
 */

public class JsonToObject {
    //Connector connector = new Connector();

    /*
    Takes a Json File of products and converts it into a list of Product objects.
    Returns and ArrayList of Products.
     */
    public ArrayList<Product> parseJsonEx(String JsonFilename) {
        ArrayList<Product> productArrayList = new ArrayList<>();

        try {
            //Statement st = connector.getConnection().createStatement();
            JsonParser parser = new JsonParser();
            JsonArray a = (JsonArray) parser.parse(new FileReader(JsonFilename, StandardCharsets.UTF_8));

            for (Object o : a) {
                JsonObject p = (JsonObject) o;

                //List of relevant product categories
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
                String cat11 = "Färdigmat & Mellanmål\n                    \n                        \n                            \n                                Pil";

                String category = "";
                String prod_name = "";
                String prod_compPrice = "";
                String unit = "";
                String cleanPrice = "";

                //Gets the name of a product
                if (p.get("prod_name") != null && !p.get("prod_name").isJsonNull()) {
                    prod_name = String.valueOf(p.get("prod_name").getAsString());
                    prod_name = prod_name.replaceAll("'", "");
                }

                //Gets the price per unit of a product
                if (p.get("prod_compPrice") != null && !p.get("prod_compPrice").isJsonNull()) {
                    prod_compPrice = String.valueOf(p.get("prod_compPrice").getAsString());

                    //Finds the measure unit to the price
                    unit = detectUnit(prod_compPrice);

                    //Isolates the floating point number from a longer string
                    String price = prod_compPrice.replaceAll("[^0-9\\.]", "");
                    StringBuilder builder = new StringBuilder();
                    builder.append(price);
                    builder.deleteCharAt(price.length()-1);
                    cleanPrice = builder.toString();
                }

                //Gets the product category
                if (p.get("Category") != null && !p.get("Category").isJsonNull()) {
                    category = String.valueOf(p.get("Category").getAsString());
                }

                //Checks whether the product belongs to a relevant category
                if ((category.equals(cat0)) || (category.equals(cat1)) || (category.equals(cat2)) ||
                        (category.equals(cat3)) || (category.equals(cat4)) || (category.equals(cat5)) ||
                        (category.equals(cat6)) || (category.equals(cat7)) || (category.equals(cat8)) ||
                        (category.equals(cat9)) || (category.equals(cat10)) || (category.equals(cat11))) {

                    if(prod_name.length() > 0 && cleanPrice.length() > 0 && unit.length() > 0) {
                        float fPrice = Float.parseFloat(cleanPrice);
                        productArrayList.add(new Product(prod_name, fPrice, unit));
                    }

                    //String query = "Insert into FoodBankDB.dbo.Livsmedel(l_namn, l_pris, l_enhet) values('" + prod_name + "'," + priceCorr9 + ",'" + unit + "');";
                    //st.executeUpdate(query);
                }
            }
            //st.close();
            //connector.getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productArrayList;
    }

    /*
    Takes in the string 'compPrice' and determines what type of unit per price it has.
    Returns either st, lit or kg as unit.
     */
    private String detectUnit(String compPrice) {
        String unit = "";
        if (compPrice.contains("kr/st")) {
            unit = "st";
        }
        if (compPrice.contains("kr/kg")) {
            unit = "kg";
        }
        if (compPrice.contains("kr/lit")) {
            unit = "lit";
        }
        if (compPrice.contains("utan")){
            unit = "kg";
        }

        return unit;
    }


//    public static void main(String[] args) {
//        JsonToObject o = new JsonToObject();
//        ArrayList<Product> list = o.parseJsonEx("files/coopSort.json");
//        for(int i = 0; i < list.size(); i++){
//            String name = list.get(i).getProd_name();
//            float price = list.get(i).getProd_price();
//            String unit = list.get(i).getUnit();
//            System.out.println(String.format("%1$60s %2$8f %3$5s", name, price, unit));
//            //System.out.println(list.get(i).getProd_name() + ", " + list.get(i).getProd_price() + ", " + list.get(i).getUnit());
//        }
//        System.out.println(list.size());
//
//        /*String str = "Jfr-pris 95.63/utan sås/spad.";
//        String price = str.replaceAll("[^0-9\\.]", "");
//        StringBuilder builder = new StringBuilder();
//        builder.append(price);
//        builder.deleteCharAt(price.length()-1);
//        price = builder.toString();
//        System.out.println(price);*/
//    }
}

