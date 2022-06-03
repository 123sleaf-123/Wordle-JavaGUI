package Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import Components.HorSpacer;
import Components.JStyleLabel;
import Components.VerSpacer;
import Supporter.InputProcessor;
import Supporter.Judgement;
import Supporter.ResourceReader;
import Supporter.WordleLogic;

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
        System.out.println("Pressed " + e.getKeyChar() + '\n' + this);
        actionDependOnInput(inputProcessor.inputProcess(e.getKeyChar()));
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
        centerPanel.setLayout(new GridLayout(6, 5, 5, 5));
        this.add(BorderLayout.CENTER, centerPanel);
        JPanel northPanel = new JPanel();
        this.add(BorderLayout.NORTH, northPanel);
        this.add(BorderLayout.WEST, new HorSpacer());
        this.add(BorderLayout.EAST, new HorSpacer());
        this.add(BorderLayout.SOUTH, new VerSpacer());

        // backBtn Settings
        ImageIcon backBtnIcon = new ImageIcon();
        try {
            Image iconImg = ImageIO.read(new File("src/resources/img/alycei_coni.png"));
            backBtnIcon.setImage(iconImg);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        backBtn.setIcon(backBtnIcon);
        backBtn.setPreferredSize(new Dimension(250, 40));
        northPanel.add(backBtn);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = new JStyleLabel();
                centerPanel.add(table[i][j]);
            }
        }
        this.setSize(600, 400);
    }

    private void actionDependOnInput(int actionCode) {
        switch (actionCode) {
            case 0:
                break;
            case 1:
            case 2:
                refresh();
                break;
            case 12: {
                String str = String.valueOf(inputProcessor.getInput()) + " is not a word!";
                WordleDialog notAWordWarning = new WordleDialog((Frame) this.getTopLevelAncestor(), str, str);
                break;
            }
            case 13: {
                String title = "Not Enough Characters!";
                WordleDialog notEnoughCharWarning = new WordleDialog((Frame) this.getTopLevelAncestor(), title, "");
                break;
            }
            case 14: {
                String title = "Empty Input!";
                String text = "Please type your keyboard to input.";
                WordleDialog EmptyInputWarning = new WordleDialog((Frame) this.getTopLevelAncestor(), title, text);
                break;
            }
            case 15: {
                wordleLogic.logicCore(inputProcessor.getInput());
                lineRefresh();
                if (Judgement.isPlayerWin(table, getCurrentRow())) {
                    String winMessage = "Game win in " + getCurrentRow()  + " rows";
                    WordleDialog EmptyInputWarning =
                            new WordleDialog((Frame) this.getTopLevelAncestor(), winMessage, "Cheers!");
                }

                if (Judgement.isFocusEnd(getCurrentRow())) {
                    WordleDialog EmptyInputWarning =
                            new WordleDialog((Frame) this.getTopLevelAncestor(), "Level: Gamer", "Game lost");
                }
                inputProcessor.clearOperation();
                setCurrentRow(getCurrentRow() + 1);
                break;
            }
        }
    }

    private void refresh() {
        char[] curInput = inputProcessor.getInput();
        for (int i = 0; i < 5; i++) {
            table[currentRow][i].setText(String.valueOf(curInput[i]));
        }
    }

    private void lineRefresh() {
        Color[] curColour = wordleLogic.getColourRes();
        for (int i = 0; i < 5; i++) {
            table[currentRow][i].setBackground(curColour[i]);
        }
    }

    public int getWordSize() {
        return wordSize;
    }

    public void setWordSize(int wordSize) {
        this.wordSize = wordSize;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
}
