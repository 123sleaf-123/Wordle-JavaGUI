package Supporter;

import java.awt.Color;
import java.util.Arrays;

/**
 * Title:        Supporter.WordleLogic.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The core judgement algorithm of the Wordle game.
 **/
public class WordleLogic {
    private int wordSize = 5; // The size (or length) of the word of the current Wordle game
    private char[] guess; // The word that the player guessed.
    private char[] wordle; // The target word to guess.
    private Color[] colourRes; // The result (colour) of a right input.

    /**
     * A private constructor that call the method to get a random wordle.
     */
    private WordleLogic() {

    }

    /**
     * A private constructor that invoke the constructor without parameters first,
     * then initialises instance variables wordle and wordleFlag with a size ${size}.
     * @param size Specify the size of the given words.
     */
    private WordleLogic(int size) {
        this();
        this.setWordSize(size);

        /*
         * use Arrays.fill to initialise colorRes to all white
         */
        this.colourRes = new Color[this.wordSize];
        Arrays.fill(this.colourRes, Color.white);
    }

    /**
     * A public constructor method to invoke another private constructor,
     * to initialise the instance variables.
     * Then, it invoke the logicCore to do the Wordle core logic processing.
     * @param wordle The target word to be guessed.
     */
    public WordleLogic(char[] wordle) {
        this(wordle.length);
        setWordle(wordle);
    }

    /**
     * Accept the valid input filtered by Supporter.InputProcessor class,
     * and calculate the result of an input word.
     * @param input A valid input filtered by Supporter.InputProcessor class.
     */
    public void logicCore(char[] input) {
        Boolean[] inputFlag, wordleFlag;

        /*
         * use Arrays.fill method to initialise wordleFlag to all false(boolean) as a mark,
         * indicating that all characters are not matched.
         */
        wordleFlag = new Boolean[wordSize];
        Arrays.fill(wordleFlag, false);

        inputFlag = new Boolean[wordSize];
        Arrays.fill(inputFlag, false);


        for (int i = 0; i < this.wordSize; i++) {
            if (input[i] == this.wordle[i]) {
                colourRes[i] = Color.GREEN;
                inputFlag[i] = wordleFlag[i] = true;
            }
            else colourRes[i] = Color.GRAY;
        }
        for (int i = 0; i < wordSize; i++) {
            if (!inputFlag[i]) {
                for (int j = 0; j < wordSize; j++) {
                    if (!wordleFlag[j]) {
                        if (input[i] == wordle[j]) {
                            colourRes[i] = Color.YELLOW;
                            inputFlag[i] = wordleFlag[j] = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void getWordleFromMemory() {}

    public int getWordSize() {
        return wordSize;
    }

    public void setWordSize(int wordSize) {
        this.wordSize = wordSize;
    }

    public char[] getGuess() {
        return guess;
    }

    public void setGuess(char[] guess) {
        this.guess = guess;
    }

    public char[] getWordle() {
        return wordle;
    }

    public void setWordle(char[] wordle) {
        this.wordle = wordle;
    }

    public Color[] getColourRes() {
        return colourRes;
    }

    public void setColourRes(Color[] colourRes) {
        this.colourRes = colourRes;
    }
}
