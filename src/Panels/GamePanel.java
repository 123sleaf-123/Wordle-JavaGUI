package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Components.JStyleLabel;
import Supportor.InputFilter;

/**
 * Title:        GamePanel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The panel for the game, extending from JPanel.
 **/
public class GamePanel extends JPanel implements KeyListener {
    private ResourceReader resourceReader;
    private InputProcessor inputProcessor;
    private WordleLogic wordleLogic;

    private JStyleLabel[][] table = new JStyleLabel[6][5];

    private char[] curInput;
    private int wordSize = 5, currentRow = 0;

    public GamePanel(JButton bckBtn) {
        initPanel(bckBtn);
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Released");
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("Pressed");
//        System.out.println(e.getKeyChar());
        System.out.println("Pressed " + e.getKeyChar() + '\n' + this);
        inputFilter.inputProcess(e.getKeyChar());
        refresh();
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("Typed ");
    }

    /**
     * Initialisation of GamePanel instance
     */
    private void initPanel(JButton backBtn) {
        resourceReader = new ResourceReader();
        inputProcessor = new InputProcessor(wordSize, resourceReader);
        wordleLogic = new WordleLogic(resourceReader.wordle);
        this.addKeyListener(this);
        this.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 5, 1, 1));
        this.add(BorderLayout.CENTER, centerPanel);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        this.add(BorderLayout.NORTH, northPanel);
        northPanel.add(backBtn);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = new JStyleLabel("test"+i+j);
                centerPanel.add(table[i][j]);
            }
        }
        this.setSize(600, 400);
    }

    private void actionDependOnInput(int actionCode) {

    }

    private void refresh() {
        char[] curInput = inputProcessor.getInput();
        for (int i = 0; i < 5; i++) {
            table[currentRow][i].setText(String.valueOf(curInput[i]));
        }
    }
}
