package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Components.JStyleLabel;
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
        switch (actionCode) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                refresh();
                break;
            case 12:
                String str = String.valueOf(inputProcessor.getInput()) + " is not a word!";
                dialogAutoGenerator(str, str);
                break;
            case 13:
                break;
            case 14:
            case 15:
                wordleLogic.logicCore(inputProcessor.getInput());
                lineRefresh();
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
        if (Judgement.isPlayerWin(table, getCurrentRow())) {
            String winMessage = "Game win in " + (getCurrentRow() + 1)  + " rows";
            dialogAutoGenerator(winMessage, "Cheers!");
        }

        if (Judgement.isFocusEnd(getCurrentRow())) {
            dialogAutoGenerator("Level: Gamer", "Game lost");
        }
        inputProcessor.clearOperation();
        setCurrentRow(getCurrentRow() + 1);
    }

    public void dialogAutoGenerator(String text, String title) {
        // Initialisation of components
        JButton okBtn = new JButton("OK"), cancelBtn = new JButton("Cancel");
        JLabel textDisplayLabel = new JLabel(text);

        // Initialisation of Containers
        JDialog notAWordWarning = new JDialog((Frame) this.getTopLevelAncestor(), title);
        notAWordWarning.setLayout(new GridLayout(2, 1));
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());

        notAWordWarning.getContentPane().add(textDisplayLabel);
        notAWordWarning.getContentPane().add(btnPanel);
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        Dimension dimension = new Dimension(300, 150);
        notAWordWarning.setMaximumSize(dimension);
        notAWordWarning.setSize(dimension);
//        notAWordWarning.setBounds(this.getBounds());
        notAWordWarning.setVisible(true);
        ActionListener dialogActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == okBtn) {
                    notAWordWarning.setVisible(false);
                }
                if (e.getSource() == cancelBtn) {
                    notAWordWarning.setVisible(false);
                }
            }
        };
        okBtn.addActionListener(dialogActionListener);
        cancelBtn.addActionListener(dialogActionListener);
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
