package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Components.JStyleLabel;
import Supportor.InputFilter;

/**
 * Title:        GamePanel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The panel for the game, extending from JPanel.
 **/
public class GamePanel extends JPanel implements KeyListener {
    private InputFilter inputFilter;

    private JStyleLabel[][] table = new JStyleLabel[6][5];
    private int size = 5;

    public GamePanel() {
        initPanel();
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Released");
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("Pressed");
//        System.out.println(e.getKeyChar());
        System.out.println("Pressed " + e.getKeyChar() + '\n' + this);
        inputFilter.inputProcess(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("Typed ");
    }

    /**
     * Initialisation of GamePanel class
     */
    public void initPanel() {
        inputFilter = new InputFilter(size);
        this.addKeyListener(this);
        this.setLayout(new GridLayout(6, 5, 1, 1));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = new JStyleLabel("test"+i+j);
                this.add(table[i][j]);
            }
        }
        this.setSize(600, 400);
    }
}
