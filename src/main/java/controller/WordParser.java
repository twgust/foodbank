package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class WordParser
{
    private LinkedList<String> regexList = new LinkedList<String>();

    private static final String filepath = "files/regexlist.txt";

    public WordParser() {
        readFile();
    }

    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            regexList.clear();
            while (true) {
                String res = reader.readLine();
                if (res == null)
                    break;
                regexList.add(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String s : regexList)
            System.out.println(s);
    }

    public boolean boolParse(String string) {
        for(String s : regexList) {
            if(string.matches(s))
                return true;
        }
        return false;
    }

    public String removeChar(String string, char ch) {
        int charIndex = string.indexOf(ch);
        System.out.println("Char index: " + charIndex);
        System.out.println("Length: " + string.length());
        if(charIndex == -1)
            return string;
        if(charIndex == string.length() - 1)
            return string.substring(0, string.length()-1);
        String result = string.substring(0, charIndex) + string.substring(charIndex, string.length());
        System.out.println(result);
        return result;
    }
}
