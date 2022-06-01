import Supportor.WordleMemory;

/**
 * Title:        InputFilter.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The class to process the initial input from the keyboard and the virtual keyboard
 * to generate suitable input to the core logic processing class.
 **/
public abstract class InputFilter{
    private char[] input;
    private int idx = 0;
    private WordleMemory ifToMemory;

    public InputFilter(int size, WordleMemory memory) {
        this.input = new char[size];
        this.ifToMemory = memory;
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

    public boolean IsAWord() {
        return true;
    }

    public void ErrorNotAWord() {

    }

    public void ErrorNotEnoughChar() {

    }
}
