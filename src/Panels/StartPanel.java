package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title:        Panels.StartPanel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The strat panel of the game.
 **/
public class StartPanel extends JPanel {
    private JButton start = new JButton("Start");
    private JButton quit = new JButton("Quit");

    public StartPanel() {
        super();
        this.setLayout(new GridLayout(2, 1));
        this.add(start);
        this.add(quit);
        this.setVisible(true);
    }

    public StartPanel(JButton start, JButton quit) {
        this.start = start;
        this.quit = quit;

        this.setLayout(new BorderLayout(this.getWidth() / 10, this.getHeight() / 10));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1));
        this.add(BorderLayout.CENTER, centerPanel);
        centerPanel.add(start);
        centerPanel.add(quit);
        this.setVisible(true);
    }
}
