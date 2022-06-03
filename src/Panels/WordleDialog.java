package Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Title:        WordleDialog.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A classical Windows dialog that extends from JDialog.
 **/
public class WordleDialog extends JDialog {
    private JButton okBtn, cancelBtn;
    private JLabel textDisplayLabel;

    public WordleDialog(Frame owner, String title, String text) {
        super(owner, title);
        // Initialisation of components
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        JLabel textDisplayLabel = new JLabel(text);

        // Initialisation of Containers
        this.setLayout(new GridLayout(2, 1));
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());

        // Icon Setting
        try {
            this.setIconImage(ImageIO.read(new File("src/resources/img/alycei_coni.png")));
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }

        this.getContentPane().add(textDisplayLabel);
        this.getContentPane().add(btnPanel);
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        Dimension dimension = new Dimension(300, 150);
        this.setMaximumSize(dimension);
        this.setSize(dimension);
        this.setBounds(owner.getX() + owner.getWidth() / 3, owner.getY() + getHeight() / 3,
                owner.getWidth() / 3, owner.getHeight() / 3);
        this.setAutoRequestFocus(true);
        this.setVisible(true);

        ActionListener dialogActionListener = e -> {
            if (e.getSource() == okBtn) {
                this.setVisible(false);
            }
            if (e.getSource() == cancelBtn) {
                this.setVisible(false);
            }
        };
        okBtn.addActionListener(dialogActionListener);
        cancelBtn.addActionListener(dialogActionListener);
    }
}
