import Panels.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Title:        WordleFrame.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The Frame of Wordle.
 **/
public class WordleFrame extends JFrame implements ActionListener {
    JButton startBtn = new JButton("Start");
    JButton quitBtn = new JButton("Quit");
    StartPanel startPanel = new StartPanel(startBtn, quitBtn);
    GamePanel gamePanel = new GamePanel();

    public WordleFrame() {
        initFrame();
    }

    public WordleFrame(String title) {
        super(title);
        initFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.startBtn) gameStart();
        if (e.getSource() == this.quitBtn) gameQuit();
    }

    public void initFrame() {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.setContentPane(startPanel);

        startBtn.addActionListener(this);
        quitBtn.addActionListener(this);
    }

    public void gameStart() {
        startPanel.setVisible(false);
        this.setContentPane(gamePanel);
        System.out.println("Start.");
    }

    public void gameQuit() {
        System.out.println("Quit.");
        System.exit(0);
    }
}
