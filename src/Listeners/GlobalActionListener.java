package Listeners;

import Panels.GamePanel;
import Panels.StartPanel;
import Panels.WordleFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title:        GlobalActionListener.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A listener for global operations, like switch panels to be displayed.
 **/
public class GlobalActionListener implements ActionListener {
    private final WordleFrame frame;
    private StartPanel startPanel;
    private GamePanel gamePanel;
    
    public GlobalActionListener(WordleFrame frame) {
        this.frame = frame;
        initialise();
    }

    /**
     * Initialise the instance
     */
    public void initialise() {
        this.startPanel = frame.getStartPanel();
        this.gamePanel = frame.getGamePanel();

        // Add all button to the GlobalActionListener
        addPanelBtn(startPanel.getCenterPanel().getComponents());
        addPanelBtn(gamePanel.getNorthPanel().getComponents());
    }

    /**
     * Adding GlobalActionListener to JButtons through array of components from panels.
     * @param components Array of components from panels.
     */
    public void addPanelBtn(Component[] components) {
        for (Component component : components) {
            if (component.getClass() == JButton.class) {
                ((JButton) component).addActionListener(this);
            }
        }
    }

    /**
     * Change the panel to the frame.getGamePanel().
     */
    public void gameStart() {
        frame.getStartPanel().setVisible(false);
        frame.setContentPane(frame.getGamePanel());
        frame.getGamePanel().setVisible(true);
        frame.getGamePanel().requestFocusInWindow();
        System.out.println("Start.");
    }

    /**
     * End the program.
     */
    public void gameExit() {
        System.out.println("Exit.");
        System.exit(0);
    }

    /**
     * Method to return to the main frame.
     */
    public void backToStart() {
        frame.getGamePanel().setVisible(false);
        frame.setContentPane(frame.getStartPanel());
        frame.getStartPanel().setVisible(true);
        frame.getStartPanel().requestFocusInWindow();
        System.out.println("Back to the start panel");
    }

    /**
     * Deal with the change of display panel.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startPanel.getStartBtn()) gameStart();
        if (e.getSource() == startPanel.getExitBtn()) gameExit();
        if (e.getSource() == gamePanel.getBackBtn()) backToStart();
    }
}
