import Panels.*;

import javax.swing.*;
import java.awt.event.*;

/**
 * Title:        WordleFrame.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The Frame of Wordle.
 **/
public class WordleFrame extends JFrame implements ActionListener, KeyListener {
    private final JButton startBtn = new JButton("Start");
    private final JButton quitBtn = new JButton("Quit");

    private final StartPanel startPanel = new StartPanel(startBtn, quitBtn);
    private final GamePanel gamePanel = new GamePanel();

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

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Released");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed " + e.getKeyChar() + '\n' + this);
//        System.out.println("Pressed");
//        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println(e.getKeyChar());
    }

    /**
     * Initialisation of the WordleFrame.
     *
     */
    public void initFrame() {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
        }catch(Exception e){
            System.out.println("Can't set this style:"+e);
        }

        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.setContentPane(startPanel);

        startBtn.addActionListener(this);
        quitBtn.addActionListener(this);
        this.addKeyListener(this);
    }

    public void gameStart() {
        startPanel.setVisible(false);
        this.setContentPane(gamePanel);
        gamePanel.setVisible(true);
        gamePanel.requestFocusInWindow();
        System.out.println("Start.");
    }

    public void gameQuit() {
        System.out.println("Quit.");
        System.exit(0);
    }
}
