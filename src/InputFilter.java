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

    public InputFilter(int size) {
        this.input = new char[size];
    }

    public void inputCheck(char c) {
        if (c >= 'a' && c <= 'z') {
            if (this.idx < 5) this.input[(this.idx)++] = (char) (c - 32);
        } else if (c >= 'A' && c <= 'Z') {
            if (this.idx < 5) this.input[(this.idx)++] = c;
        } else if (c == '\n') {
            if (this.idx == 5)
                if (IsAWord()) return;
                else ErrorNotAWord();
            else ErrorNotEnoughChar();
        } else if (c == '\b') {
            if (this.idx >= 0) this.input[this.idx--] = 0;
        }
        System.out.println(this.input);
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

    public abstract boolean IsAWord();

    public abstract void ErrorNotAWord();

    public abstract void ErrorNotEnoughChar();
}
