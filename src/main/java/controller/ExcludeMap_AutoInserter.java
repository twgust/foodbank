package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * part of relationsautoinserter project, don't know what that app does
 */
public class ExcludeMap_AutoInserter {
    private static final String filepath = "files/excludemap.txt";
    private HashMap<String, String> map = new HashMap<String, String>();

    public ExcludeMap_AutoInserter() {
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
            return null;
        return map.get(string);
    }
}