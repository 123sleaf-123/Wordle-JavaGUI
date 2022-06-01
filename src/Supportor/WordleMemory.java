package Supportor;

import java.io.*;
import java.util.ArrayList;

/**
 * Title:        Supportor.WordleMemory.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description Memory manager of Wordle.
 **/
public class WordleMemory {
    private ArrayList<String> dictionary = new ArrayList<>();
    private String guess;

    private char[] input;
    private int idx = 0;

    public WordleMemory() {
        this.queryWords();
        this.input = new char[5];
    }

    public WordleMemory(int size) {
        this.queryWords();
        this.input = new char[size];
    }
    public void queryWords() {
        File f = new File("resources/dictionary.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str;
            do {
                str = br.readLine();
                dictionary.add(str);
            } while (str != null);
        } catch (IOException e) {
            System.out.println("Error");
            System.exit(-1);
        }
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(ArrayList<String> dictionary) {
        this.dictionary = dictionary;
    }

}
