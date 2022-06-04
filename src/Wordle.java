import Listeners.GlobalActionListener;
import Panels.WordleFrame;

/**
 * Title:        Wordle.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The main program of Wordle.
 **/
public class Wordle {
    public static void main(String[] args) {
        WordleFrame frame = new WordleFrame();
        GlobalActionListener globalActionListener = new GlobalActionListener(frame);
    }
}
