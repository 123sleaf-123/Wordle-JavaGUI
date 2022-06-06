package listeners.Key;

import containers.GamePanel;
import supporter.Judgement;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Title:        GamePanelKeyListener.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A KeyListener implements KeyListener interface for GamePanel class.
 **/
public class GamePanelKeyListener implements KeyListener {
    private GamePanel gamePanel;

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

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
                gamePanel.refresh();
                break;
            case 12: {
                String str = String.valueOf(gamePanel.getInputProcessor().getInput()) + " is not a word!";
                JOptionPane.showMessageDialog(gamePanel,
                        str, "Not A Word!", JOptionPane.ERROR_MESSAGE);
                break;
            }
            case 13: {
                String title = "Not Enough Characters!";
                JOptionPane.showMessageDialog(gamePanel, null, title, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 14: {
                String title = "Empty Input!";
                String text = "Please type your keyboard to input.";
//                emptyInputWarning = new WordleDialog((Frame) this.getTopLevelAncestor(), title, text);
                JOptionPane.showMessageDialog(gamePanel, text, title, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 15: {
                gamePanel.getWordleLogic().logicCore(gamePanel.getInputProcessor().getInput());
                gamePanel.lineRefresh();
                if (Judgement.isPlayerWin(gamePanel.getTable(), gamePanel.getCurrentRow())) {
                    String winMessage = "Game win in " + gamePanel.getCurrentRow() + " rows";
//                    winDialog = new WordleDialog((JFrame) this.getTopLevelAncestor(), winMessage, "Cheers!");
                    int choice = JOptionPane.showConfirmDialog(gamePanel,
                            winMessage, "Cheers!", JOptionPane.PLAIN_MESSAGE);
                }

                if (Judgement.isFocusEnd(gamePanel.getCurrentRow())) {
                    int choice = JOptionPane.showConfirmDialog(gamePanel,
                            "Wordle: " + String.valueOf(gamePanel.getWordleLogic().getWordle()) +
                                    ".    Level: Gamer", "Game lost", JOptionPane.PLAIN_MESSAGE);
                }
                gamePanel.getInputProcessor().clearOperation();
                gamePanel.setCurrentRow(gamePanel.getCurrentRow() + 1);
                break;
            }
        }
    }
}
