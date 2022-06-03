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
import Panels.Dialog.WordleDialog;
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
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private ResourceReader resourceReader;
    private InputProcessor inputProcessor;
    private WordleLogic wordleLogic;

    private JStyleLabel[][] table = new JStyleLabel[6][5];

    private int wordSize = 5, currentRow = 0;

    public GamePanel(JButton bckBtn) {
        initPanel(bckBtn);
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
        this.add(BorderLayout.WEST, new HorSpacer(20));
        this.add(BorderLayout.EAST, new HorSpacer(20));
        this.add(BorderLayout.SOUTH, new VerSpacer(this, 2, 20));

        // northPanel Settings

        // backBtn Icon Settings
        backBtn.addActionListener(this);

        // backBtn Icon Settings
        ImageIcon backBtnIicon = new ImageIcon();
        try {
            Image iconImg = ImageIO.read(new File("src/resources/img/Medico.png"));
            backBtnIicon.setImage(iconImg);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        backBtn.setIcon(backBtnIicon);
        backBtn.setPreferredSize(new Dimension(220, 80));
        northPanel.add(backBtn);

        // resetBtn Settings
        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(this);

        // resetBtn Icon Settings
        ImageIcon resetBtnIcon = new ImageIcon();
        try {
            Image iconImg = ImageIO.read(new File("src/resources/img/Kikuchiyo.png"));
            resetBtnIcon.setImage(iconImg);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        resetBtn.setIcon(resetBtnIcon);
        resetBtn.setPreferredSize(new Dimension(220, 80));
        northPanel.add(resetBtn);


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = new JStyleLabel();
                centerPanel.add(table[i][j]);
            }
        }
        this.setSize(600, 400);
    }

    /**
     * actions depend on return operation codes
     * @param actionCode return operation codes from InputProcessor
     */
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
                            new WordleDialog((Frame) this.getTopLevelAncestor(), "Game lost", "Level: Gamer");
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

    /**
     * Clear all temperate variables and initialise them
     */
    public void clear() {
        System.out.println("Start clear.");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j].setText("");
                table[i][j].setBackground(Color.WHITE);
            }
        }
        setCurrentRow(0);
        resourceReader.reset();
        inputProcessor.reset();
        wordleLogic.reset(resourceReader.getWordle());
        refresh();
        lineRefresh();
    }


    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Released");
    }

    /**
     * Print the input message and deal with the input
     * @param e the event to be processed
     */
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
     * If the event was caused by a JButton, the method call clear() method.
     * If the event was caused by JButton backBtn, then it back to StartPanel.
     * Otherwise, GamePanel ask for focus by calling requestFocus() method.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass() == (new JButton()).getClass()) {
            clear();
            WordleFrame father = (WordleFrame) this.getTopLevelAncestor();
            if (e.getSource() == father.getBackBtn()) father.backToStart();
            else this.requestFocus();
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
