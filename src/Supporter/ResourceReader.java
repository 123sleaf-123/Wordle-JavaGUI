package Supporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Title:        ResourceReader.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A class to get resources from text files or databases.
 **/
public class ResourceReader {

    private ArrayList<String> dictionary = new ArrayList<>();
    public char[] wordle;

    public ResourceReader() {
        queryWords();
        generateWordle();
    }

    public void queryWords() {
        File f = new File("resources/dictionary.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                dictionary.add(str.toUpperCase());
                str = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error");
            System.exit(-1);
        }
    }

    /**
     * A method to generate the target word from the dictionary
     */
    private void generateWordle() {
        Random random = new Random();
        wordle = dictionary.get(random.nextInt(0, dictionary.size())).toCharArray();
    }

    /**
     * Judge that whether the input char array char[5] input is a word.
     * @return true, when it's a word; Otherwise false.
     */
    public boolean searchWord(char[] input) {
        return dictionary.contains(String.valueOf(input));
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(ArrayList<String> dictionary) {
        this.dictionary = dictionary;
    }

    public char[] getWordle() {
        return wordle;
    }

    public void setWordle(char[] wordle) {
        this.wordle = wordle;
    }
}
