package Containers;

import Components.HorSpacer;
import Components.JStyleButton;
import Components.VerSpacer;

import javax.swing.*;
import java.awt.*;

/**
 * Title:        Containers.StartPanel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The strat panel of the game.
 **/
public class StartPanel extends JPanel {
    private final JStyleButton startBtn = new JStyleButton("Start");
    private final JStyleButton settingBtn = new JStyleButton("Settings");
    private final JStyleButton rankBtn = new JStyleButton("Ranks");
    private final JStyleButton exitBtn = new JStyleButton("Exit");
    private final JLabel gameTitle = new JLabel();
    private JPanel centerPanel;
    private HorSpacer westHorSpacer;
    private HorSpacer eastHorSpacer;
    private VerSpacer southVerSpacer;

    public StartPanel() {
        super();
        initPanel();
    }

    private void initPanel() {
        this.setLayout(new BorderLayout(this.getWidth() / 10, this.getHeight() / 10));

        gameTitle.setMinimumSize(new Dimension(200, 150));
        gameTitle.setFont(new Font("Arial Black", Font.PLAIN, 72));
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("src/resources/img/Wordle.png");
        gameTitle.setIcon(icon);
        gameTitle.setOpaque(false);

        centerPanel = new JPanel();
//        centerPanel.setMinimumSize(new Dimension(100, 150));
        centerPanel.setMaximumSize(new Dimension(100, 150));
        centerPanel.setLayout(new GridLayout(5, 1, 10, 15));
        centerPanel.add(new VerSpacer(this, 2, 50));
        centerPanel.add(startBtn);
        centerPanel.add(settingBtn);
        centerPanel.add(rankBtn);
        centerPanel.add(exitBtn);

        this.add(BorderLayout.NORTH, gameTitle);
        this.add(BorderLayout.CENTER, centerPanel);
        westHorSpacer = new HorSpacer();
        this.add(BorderLayout.WEST, westHorSpacer);
        eastHorSpacer = new HorSpacer();
        this.add(BorderLayout.EAST, eastHorSpacer);
        southVerSpacer = new VerSpacer(this);
        this.add(BorderLayout.SOUTH, southVerSpacer);

        // Set Opaque false
        this.setOpaque(false);
        centerPanel.setOpaque(false);
    }

    public JStyleButton getStartBtn() {
        return startBtn;
    }

    public JStyleButton getSettingBtn() {
        return settingBtn;
    }

    public JStyleButton getRankBtn() {
        return rankBtn;
    }

    public JStyleButton getExitBtn() {
        return exitBtn;
    }

    public JLabel getGameTitle() {
        return gameTitle;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
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
}
