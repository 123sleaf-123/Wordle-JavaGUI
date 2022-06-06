package listeners.Action;

import containers.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title:        GamePanelActionListener.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description An ActionListener implements ActionListener interface for GamePanel class.
 **/
public class GamePanelActionListener implements ActionListener {
    private final GamePanel gamePanel;

    /**
     * Initialise GamePanelActionListener instance by storing the user instance GamePanel
     * @param gamePanel the user instance
     */
    public GamePanelActionListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initialise();
    }

    /**
     * Initialisation of GamePanelActionListener instance
     */
    private void initialise() {
        gamePanel.getBackBtn().addActionListener(this);
        gamePanel.getResetBtn().addActionListener(this);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gamePanel.getBackBtn() || e.getSource() == gamePanel.getResetBtn()) {
            gamePanel.clear();
        }
        if (e.getSource() == gamePanel.getResetBtn()) {
            gamePanel.clear();
            gamePanel.requestFocus();
        }
    }
}
