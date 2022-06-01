package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Title:        GamePanel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The panel for the game, extending from JPanel.
 **/
public class GamePanel extends JPanel {
    private ArrayList<JLabel[]> table = new ArrayList<JLabel[]>(6);
    private JLabel[] row = new JLabel[5];
    private int size = 5;

    public GamePanel() {
        this.setLayout(new GridLayout(6, 5, 1, 1));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                row[j] = new JLabel("test"+i+j);;
                this.add(row[j]);
            }
            table.add(row);
        }
        this.setSize(600, 400);
    }
}
