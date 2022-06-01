package Panels;

import javax.swing.*;
import java.awt.*;
import Components.JStyleLabel;

/**
 * Title:        GamePanel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The panel for the game, extending from JPanel.
 **/
public class GamePanel extends JPanel {
    private JStyleLabel[][] table = new JStyleLabel[6][5];
    private int size = 5;

    public GamePanel() {
        this.setLayout(new GridLayout(6, 5, 1, 1));
        initPanel();
    }

    /**
     * Initialisation of GamePanel class
     */
    public void initPanel() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = new JStyleLabel("test"+i+j);
                this.add(table[i][j]);
            }
        }
        this.setSize(600, 400);
    }
}
