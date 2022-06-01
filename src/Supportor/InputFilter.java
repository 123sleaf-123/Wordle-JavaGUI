package Supportor;

import Supportor.WordleMemory;

/**
 * Title:        Supportor.InputFilter.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The class to process the initial input from the keyboard and the virtual keyboard
 * to generate suitable input to the core logic processing class.
 **/
public class InputFilter{
    private ArrayList<String> dictionary = new ArrayList<>();
    private char[] input;
    private int idx = 0;

    public  InputFilter(int size) {
        input = new char[size];
    }

    public void inputProcess(char c) {
        if (c >= 'a' && c <= 'z') {
            if (this.idx < this.input.length) this.input[(this.idx)++] = (char) (c - 32);
        } else if (c >= 'A' && c <= 'Z') {
            if (this.idx < this.input.length) this.input[(this.idx)++] = c;
        } else if (c == '\n') {
            if (this.idx == this.input.length)
                if (IsAWord()) return;
                else ErrorNotAWord();
            else ErrorNotEnoughChar();
        } else if (c == '\b') {
            if (this.idx >= 0) this.input[this.idx--] = 0;
        }
    }

    /**
     * The method to ask GameController to refresh the WordleFrame.
     */
    public void refreshRequest() {

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

    public boolean IsAWord() {
        return true;
    }

    public int ErrorNotAWord() {
        System.out.println(String.valueOf(input) + " is not a word.");
        return 2;
    }

    public int ErrorNotEnoughChar() {
        return 3;
    }

    public char[] getInput() {
        return input;
    }

    public void setInput(char[] input) {
        this.input = input;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
