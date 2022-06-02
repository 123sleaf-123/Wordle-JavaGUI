import Panels.*;

import javax.swing.*;
import java.awt.*;
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
    private final JButton backBtn = new JButton("Back");

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
        if (e.getSource() == startBtn) gameStart();
        if (e.getSource() == quitBtn) gameQuit();
        if (e.getSource() == backBtn) ;
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Released");
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("Pressed " + e.getKeyChar() + '\n' + this);
//        System.out.println("Pressed");
//        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println(e.getKeyChar());
    }

    /**
     * Initialisation of the WordleFrame instance.
     */
    public void initFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
        } catch (Exception e) {
            System.out.println("Can't set this style:" + e);
        }

        // Set the size and position of the frame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int width = screenWidth / 2;
        int height = screenHeight / 2;
        int x = screenWidth / 4;
        int y = screenHeight / 4;
        this.setSize(width,height);
        this.setBounds(x, y, width, height);

        // Set Content Panel
        this.setContentPane(startPanel);

        // Settings
        this.setTitle("Wordle Power");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Add listeners
        startBtn.addActionListener(this);
        quitBtn.addActionListener(this);
        backBtn.addActionListener(this);
        this.addKeyListener(this);
    }

    /**
     * Change the panel to the GamePanel.
     */
    public void gameStart() {
        startPanel.setVisible(false);
        this.setContentPane(gamePanel);
        gamePanel.setVisible(true);
        gamePanel.requestFocusInWindow();
        System.out.println("Start.");
    }

    /**
     * End the program.
     */
    public void gameQuit() {
        System.out.println("Quit.");
        System.exit(0);
    }

    public void backToStart() {
        gamePanel.setVisible(false);
        this.setContentPane(startPanel);
        startPanel.setVisible(true);
        startPanel.requestFocusInWindow();
        System.out.println("Back to the start panel");
    }
}
