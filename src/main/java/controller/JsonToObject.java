package controller;

import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class JsonToObject {
    Connector connector = new Connector();



    //Product product = new Product();


    private void parseJsonEx() {

        try {
            Statement st = connector.getConnection().createStatement();
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
                String cat11 = "Färdigmat & Mellanmål\n                    \n                        \n                            \n                                Pil";

             //   String prod_name = String.valueOf(p.get("prod_name").getAsString());
               // String prod_compPrice = String.valueOf(p.get("prod_compPrice").getAsString());
              //  int idx = prod_price.lastIndexOf(':') +1;
              //  String priceCorr = prod_price.substring(0, idx).replace(':', '.') + prod_price.substring(idx);



                String categori = "";
                String prod_name = "";
                String prod_compPrice = "";
                String prod_nname = "";


                String priceCorr2 = "";
                String priceCorr3 = "";
                String priceCorr4 = "";
                String priceCorr5 = "";
                String priceCorr6 = "";
                String priceCorr7 = "";
                String priceCorr8 = "";
                String priceCorr9 = "";

                connector.getConnection();

                if(p.get("prod_name") != null && !p.get("prod_name").isJsonNull()) {
                    prod_name = String.valueOf(p.get("prod_name").getAsString());
                    prod_nname = prod_name.replace("'", "");


                }

                if(p.get("prod_compPrice") != null &&!p.get("prod_compPrice").isJsonNull()) {
                    prod_compPrice = String.valueOf(p.get("prod_compPrice").getAsString());
                    priceCorr2 = prod_compPrice.replace("Jfr-pris ", "");
                    priceCorr3 = priceCorr2.replace("/kr/kg.", "");
                    priceCorr4 = priceCorr3.replace("/kr/lit.", "");
                    priceCorr5 = priceCorr4.replace("/kr/st.", "");
                    priceCorr6 = priceCorr5.replace("/utan sås/spad.", "");
                    priceCorr7 = priceCorr6.replace("/kr/lit drickfärdig.", "");
                    priceCorr8 = priceCorr7.replace(" ", "");
                    priceCorr9 = priceCorr8.replace("kr/kg ätklar. ", "");


                }


                if(p.get("Category") != null &&!p.get("Category").isJsonNull()) {
                    categori = String.valueOf(p.get("Category").getAsString());

                }


                if ((categori.equals(cat0)) || (categori.equals(cat1)) || (categori.equals(cat2)) ||
                        (categori.equals(cat3)) || (categori.equals(cat4)) || (categori.equals(cat5)) ||
                        (categori.equals(cat6)) || (categori.equals(cat7)) || (categori.equals(cat8)) || (categori.equals(cat9)) || (categori.equals(cat10)) || (categori.equals(cat11)))
                {



                   String query = "Insert into FoodBankDB.dbo.Livsmedel(l_namn,l_pris) values('"+prod_name+"',"+priceCorr9+");";
                   st.executeUpdate(query);


                }


            }
            st.close();
            connector.getConnection().close();
        } catch (Exception e) {
        e.printStackTrace();
          }


    }


        public static void main (String[]args){


        JsonToObject o = new JsonToObject();
        o.parseJsonEx();



        }

    }

