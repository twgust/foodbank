package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class WordMap
{
    private static final String filepath = "files/wordmap.txt";
    private HashMap<String, String> map = new HashMap<String, String>();

    public WordMap() {
        readWordMap();
    }

    private void readWordMap() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            map.clear();
            while(true) {
                String value = reader.readLine();
                if(value == null)
                    break;
                if(value != "-") {
                    while(true) {
                        String key = reader.readLine();
                        if(key == null || key.equals("-"))
                            break;
                        System.out.println("Key: " + key + " Value: " + value);
                        map.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String string) {
        if(!map.containsKey(string))
            return string;
        return map.get(string);
    }
}
