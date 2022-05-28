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
    private ArrayList<String> dictionary = new ArrayList<String>();
    private String guess;

    public WordleMemory() {
        this.queryWords();
    }
    public void queryWords() {
        File f = new File("resources/dictionary.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                str = br.readLine();
            }
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

    public static void main(String[] args) {
        WordleMemory m = new WordleMemory();
        m.queryWords();
        System.out.println(m.getDictionary());
    }
}
