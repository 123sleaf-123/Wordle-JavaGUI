package Containers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Title:        Containers.WordleFrame.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The Frame of Wordle.
 **/
public class WordleFrame extends JFrame {
    private final StartPanel startPanel = new StartPanel();
    private final GamePanel gamePanel = new GamePanel();

    public WordleFrame() {
        initFrame();
    }

    public WordleFrame(String title) {
        super(title);
        initFrame();
    }

    /**
     * Initialisation of the Containers.WordleFrame instance.
     */
    public void initFrame() {
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//            SwingUtilities.updateComponentTreeUI(this);
//            this.pack();
//        } catch (Exception e) {
//            System.out.println("Can't set this style:" + e);
//        }

        // Set the size and position of the frame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int width = screenWidth / 3;
        int height = (int) (screenHeight / 1.5);
        int x = screenWidth / 4;
        int y = screenHeight / 4;
        this.setSize(width, height);
        this.setBounds(x, y, width, height);
        this.setResizable(false);

        // Icon Setting
        try {
            this.setIconImage(ImageIO.read(new File("src/resources/img/alycei_coni.png")));
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        // Background Image Setting
        ImageIcon icon = new ImageIcon("src/resources/img/coverageDohna1.jpg");
        JLabel imgLabel = new JLabel(icon);
        imgLabel.setSize(this.getSize());
        imgLabel.setOpaque(true);
        imgLabel.setBackground(new Color(255, 51, 140));
        this.getLayeredPane().add(imgLabel, Integer.valueOf(Integer.MIN_VALUE));

        // Set Content Panel
        this.setContentPane(startPanel);

        // Settings
        this.setTitle("Wordle Power");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
