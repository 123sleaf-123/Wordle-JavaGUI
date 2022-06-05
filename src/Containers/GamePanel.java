package Containers;

import Components.HorSpacer;
import Components.JStyleButton;
import Components.JStyleLabel;
import Components.VerSpacer;
import DataClass.Record;
import Listeners.Action.GamePanelActionListener;
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

    private GamePanelActionListener actionListener;

    private int wordSize = 5, currentRow = 0;
    private long begin, end;

    private final JStyleButton backBtn = new JStyleButton("Back", JStyleButton.PINK);
    private final JStyleButton resetBtn = new JStyleButton("Reset", JStyleButton.PINK);

    /**
     * The components to form a table to display the current and previous input and results
     */
    private final JStyleLabel[][] table = new JStyleLabel[6][5];

    private JPanel centerPanel;
    private JPanel northPanel;

    /**
     * Spacer to adjust components' sizes
     */
    private HorSpacer westHorSpacer;

    /**
     * Spacer to adjust components' sizes
     */
    private HorSpacer eastHorSpacer;

    /**
     * Spacer to adjust components' sizes
     */
    private VerSpacer southVerSpacer;

    /**
     * Constructor Method to initialise class itself
     */
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
        actionListener  = new GamePanelActionListener(this);
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

        begin = System.currentTimeMillis();
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
        backBtn.setPreferredSize(new Dimension(240, 80));
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
        resetBtn.setPreferredSize(new Dimension(240, 80));
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
                String str = String.valueOf(getInputProcessor().getInput()) + " is not a word!";
                JOptionPane.showMessageDialog(this,
                        str, "Not A Word!", JOptionPane.ERROR_MESSAGE);
                break;
            }
            case 13: {
                String title = "Not Enough Characters!";
                String text = "Current input is " + String.valueOf(inputProcessor.getInput()) +
                        ", length is " + inputProcessor.getIdx() + ".\n" + title;
                JOptionPane.showMessageDialog(this,
                        text, title, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 14: {
                String title = "Empty Input!";
                String text = "Please type your keyboard to input.";
                JOptionPane.showMessageDialog(this, text, title, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 15: {
                getWordleLogic().logicCore(getInputProcessor().getInput());
                lineRefresh();
                if (! endJudge()) {
                    inputProcessor.clearOperation();
                    setCurrentRow(getCurrentRow() + 1);
                }
                break;
            }
        }
    }

    /**
     * Use Judgement to judge whether a game has ended.
     * @return whether to do continue operation.
     */
    public boolean endJudge() {
        if (Judgement.isPlayerWin(getTable(), getCurrentRow())) {
            end = System.currentTimeMillis();
            int duration = (int) ((end - begin) / 1000);
            Record winRecord = new Record();
            resourceReader.endRecord(true, this, winRecord);
            String winMessage = "Game win in " + (getCurrentRow() + 1) + " row(s) within " +
                    duration + "s.\n" + "You have beaten " +
                    ((Double) (resourceReader.readRecord(duration) * 100)).intValue() +
                    " percentages of players";
            String title = "Cheers!";
            int choice = JOptionPane.showConfirmDialog(this,
                    winMessage, title, JOptionPane.YES_NO_OPTION);
            ((WordleFrame) this.getTopLevelAncestor()).getGlobalActionListener().backToStart();
            clear();
            return true;
        }

        if (Judgement.isFocusEnd(getCurrentRow())) {
            end = System.currentTimeMillis();
            Record lostRecord = new Record();
            resourceReader.endRecord(false, this, lostRecord);
            String lostMessage = "Wordle: " + String.valueOf(getWordleLogic().getWordle()) +
                    ".\nLevel: Gamer.\n Game Lost.";
            int choice = JOptionPane.showConfirmDialog(this,
                    lostMessage, "Game lost", JOptionPane.YES_NO_OPTION);
            ((WordleFrame) this.getTopLevelAncestor()).getGlobalActionListener().backToStart();
            clear();
            return true;
        }
        return false;
    }

    /**
     * Refresh current line JStyleLabels to display the current input
     */
    public void refresh() {
        char[] curInput = inputProcessor.getInput();
        for (int i = 0; i < 5; i++) {
            table[currentRow][i].setText(String.valueOf(curInput[i]));
        }
    }

    /**
     * Refresh the current line JStyleLabels to display the result colours.
     */
    public void lineRefresh() {
        Color[] curColour = wordleLogic.getColourRes();
        for (int i = 0; i < 5; i++) {
            table[currentRow][i].setBackground(curColour[i]);
        }
    }

    /**
     * Clear all temperate variables and initialise them.
     * When backBtn or resetBtn is pressed.
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


    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Released");
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     * Print the input message and deal with the input
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed " + e.getKeyChar() + '\n' + this);
        actionDependOnInput(inputProcessor.inputProcess(e.getKeyChar()));
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("Typed ");
    }

    public ResourceReader getResourceReader() {
        return resourceReader;
    }

    public void setResourceReader(ResourceReader resourceReader) {
        this.resourceReader = resourceReader;
    }

    public InputProcessor getInputProcessor() {
        return inputProcessor;
    }

    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public WordleLogic getWordleLogic() {
        return wordleLogic;
    }

    public void setWordleLogic(WordleLogic wordleLogic) {
        this.wordleLogic = wordleLogic;
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

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

}
