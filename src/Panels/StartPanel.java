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
        this.setLayout(new GridLayout(2, 1));
        this.add(start);
        this.add(quit);
        this.setVisible(true);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == this.start) gameStart();
//        if (e.getSource() == this.quit) gameQuit();
//    }
//
//    public void gameStart() {
//        this.setVisible(false);
//        System.out.println("Start.");
//    }
//
//    public void gameQuit() {
//        System.out.println("Quit.");
//        System.exit(0);
//    }

//    public static void main(String[] args) {
//        JFrame testFrame = new JFrame("test");
//        testFrame.setContentPane(new Panels.StartPanel());
//        testFrame.setSize(600, 400);
//        testFrame.setVisible(true);
//    }
}
