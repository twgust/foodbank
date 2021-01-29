package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class WordList
{
    private static LinkedList<String> words = new LinkedList<String>();
    private static final String filepath = "files/wordlist.txt";

    public WordList()
    {
        readWordList();
    }

    public boolean contains(String string)
    {
        return words.contains(string);
    }

    private void readWordList()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            words.clear();
            while (true)
            {
                String res = reader.readLine();
                if (res == null)
                {
                    break;
                }
                words.add(res);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        for (String s : words)
        {
            System.out.println(s);
        }
    }
}
