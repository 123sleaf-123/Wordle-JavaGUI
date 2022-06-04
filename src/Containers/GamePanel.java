package Containers;

import Components.HorSpacer;
import Components.JStyleButton;
import Components.JStyleLabel;
import Components.VerSpacer;
import Containers.Dialog.WordleDialog;
import Supporter.InputProcessor;
import Supporter.Judgement;
import Supporter.ResourceReader;
import Supporter.WordleLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

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

    private final JStyleButton backBtn = new JStyleButton("Back");
    private final JStyleButton resetBtn = new JStyleButton("Reset");
    private final JStyleLabel[][] table = new JStyleLabel[6][5];

    private int wordSize = 5, currentRow = 0;
    private JPanel centerPanel;
    private JPanel northPanel;
    private HorSpacer westHorSpacer;
    private HorSpacer eastHorSpacer;
    private VerSpacer southVerSpacer;
    private WordleDialog notAWordWarning;
    private WordleDialog notEnoughCharWarning;
    private WordleDialog emptyInputWarning;
    private WordleDialog winDialog;
    private WordleDialog focusEndDialog;

    public GamePanel() {
        initialise();
    }

    /**
     * Initialisation of GamePanel instance
     */
    private void initialise() {
        resourceReader = new ResourceReader();
        inputProcessor = new InputProcessor(wordSize, resourceReader);
        wordleLogic = new WordleLogic(resourceReader.wordle);
        this.addKeyListener(this);
        this.setLayout(new BorderLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 5, 5, 5));
        this.add(BorderLayout.CENTER, centerPanel);
        northPanel = new JPanel();
        this.add(BorderLayout.NORTH, northPanel);
        westHorSpacer = new HorSpacer(20);
        this.add(BorderLayout.WEST, westHorSpacer);
        eastHorSpacer = new HorSpacer(20);
        this.add(BorderLayout.EAST, eastHorSpacer);
        southVerSpacer = new VerSpacer(this, 2, 20);
        this.add(BorderLayout.SOUTH, southVerSpacer);

        // backBtn Icon Settings
        initBackBtn();

        // resetBtn Icon Settings
        initResetBtn();
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = new JStyleLabel();
                centerPanel.add(table[i][j]);
            }
        }
        this.setSize(600, 400);
    }

    /**
     * Initialise backBtn, backBtn icon settings
     */
    private void initBackBtn() {
        ImageIcon backBtnIcon = new ImageIcon();
        try {
            Image iconImg = ImageIO.read(new File("src/resources/img/Medico.png"));
            backBtnIcon.setImage(iconImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        backBtn.setIcon(backBtnIcon);
        backBtn.setPreferredSize(new Dimension(220, 80));
        northPanel.add(backBtn);
    }

    /**
     * Initialise resetBtn, resetBtn icon settings
     */
    private void initResetBtn() {
        ImageIcon resetBtnIcon = new ImageIcon();
        try {
            Image iconImg = ImageIO.read(new File("src/resources/img/Kikuchiyo.png"));
            resetBtnIcon.setImage(iconImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resetBtn.setIcon(resetBtnIcon);
        resetBtn.setPreferredSize(new Dimension(220, 80));
        northPanel.add(resetBtn);
    }

    /**
     * actions depend on return operation codes
     *
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
                notAWordWarning = new WordleDialog((Frame) this.getTopLevelAncestor(), str, str);
                break;
            }
            case 13: {
                String title = "Not Enough Characters!";
                notEnoughCharWarning = new WordleDialog((Frame) this.getTopLevelAncestor(), title, "");
                break;
            }
            case 14: {
                String title = "Empty Input!";
                String text = "Please type your keyboard to input.";
                emptyInputWarning = new WordleDialog((Frame) this.getTopLevelAncestor(), title, text);
                break;
            }
            case 15: {
                wordleLogic.logicCore(inputProcessor.getInput());
                lineRefresh();
                if (Judgement.isPlayerWin(table, getCurrentRow())) {
                    String winMessage = "Game win in " + getCurrentRow() + " rows";
                    winDialog = new WordleDialog((JFrame) this.getTopLevelAncestor(), winMessage, "Cheers!");
                }

                if (Judgement.isFocusEnd(getCurrentRow())) {
                    focusEndDialog = new WordleDialog((JFrame) this.getTopLevelAncestor(),
                            "Game lost", "Wordle: " +
                            String.valueOf(wordleLogic.getWordle()) + ".    Level: Gamer");
                    WordleFrame father = (WordleFrame) this.getTopLevelAncestor();
//                    focusEndDialog.getOkBtn().addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            father.backToStart();
//                        }
//                    });
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
     *
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

    public JStyleButton getBackBtn() {
        return backBtn;
    }

    public JStyleButton getResetBtn() {
        return resetBtn;
    }

    public JStyleLabel[][] getTable() {
        return table;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public void setNorthPanel(JPanel northPanel) {
        this.northPanel = northPanel;
    }

    public HorSpacer getWestHorSpacer() {
        return westHorSpacer;
    }

    public void setWestHorSpacer(HorSpacer westHorSpacer) {
        this.westHorSpacer = westHorSpacer;
    }

    public HorSpacer getEastHorSpacer() {
        return eastHorSpacer;
    }

    public void setEastHorSpacer(HorSpacer eastHorSpacer) {
        this.eastHorSpacer = eastHorSpacer;
    }

    public VerSpacer getSouthVerSpacer() {
        return southVerSpacer;
    }

    public void setSouthVerSpacer(VerSpacer southVerSpacer) {
        this.southVerSpacer = southVerSpacer;
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
