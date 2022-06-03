package Panels;

import Components.HorSpacer;
import Components.JStyleLabel;
import Components.VerSpacer;

import javax.swing.*;
import java.awt.*;

/**
 * Title:        Panels.StartPanel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The strat panel of the game.
 **/
public class StartPanel extends JPanel {
    private JButton startBtn = new JButton("Start");
    private JButton settingBtn = new JButton("Settings");
    private JButton rankBtn = new JButton("Ranks");
    private JButton quitBtn = new JButton("Quit");
    private JLabel gameTitle = new JLabel("Wordle");

    public StartPanel() {
        super();
        this.setLayout(new GridLayout(2, 1));
        this.add(startBtn);
        this.add(quitBtn);
        this.setVisible(true);
    }

    public StartPanel(JButton startBtn, JButton quitBtn) {
        this.setLayout(new BorderLayout(this.getWidth() / 10, this.getHeight() / 10));

        gameTitle.setMinimumSize(new Dimension(200, 150));
        gameTitle.setFont(new Font("Arial Black", Font.PLAIN , 72));
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel();
//        centerPanel.setMinimumSize(new Dimension(100, 150));
        centerPanel.setMaximumSize(new Dimension(100, 150));
        centerPanel.setLayout(new GridLayout(5, 1, 10, 15));
        centerPanel.add(new VerSpacer(2, 50));
        centerPanel.add(startBtn);
        centerPanel.add(settingBtn);
        centerPanel.add(rankBtn);
        centerPanel.add(quitBtn);

        this.add(BorderLayout.NORTH, gameTitle);
        this.add(BorderLayout.CENTER, centerPanel);
        this.add(BorderLayout.WEST, new HorSpacer());
        this.add(BorderLayout.EAST, new HorSpacer());
        this.add(BorderLayout.SOUTH, new VerSpacer());
        this.setVisible(true);
    }
}
