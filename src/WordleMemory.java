import java.io.*;
import java.util.ArrayList;

/**
 * Title:        WordleMemory.java
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

    public void update(InputFilter filter) {
        this.idx = filter.getIdx();
        this.input = filter.getInput();
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(ArrayList<String> dictionary) {
        this.dictionary = dictionary;
    }

}
