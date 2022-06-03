package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        // Initialisation of components
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        JLabel textDisplayLabel = new JLabel(text);

        // Initialisation of Containers
        JDialog dialog = new JDialog(owner, title);
        dialog.setLayout(new GridLayout(2, 1));
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());

        dialog.getContentPane().add(textDisplayLabel);
        dialog.getContentPane().add(btnPanel);
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        Dimension dimension = new Dimension(300, 150);
        dialog.setMaximumSize(dimension);
        dialog.setSize(dimension);
//        dialog.setBounds(this.getBounds());
        dialog.setVisible(true);
        ActionListener dialogActionListener = e -> {
            if (e.getSource() == okBtn) {
                dialog.setVisible(false);
            }
            if (e.getSource() == cancelBtn) {
                dialog.setVisible(false);
            }
        };
        okBtn.addActionListener(dialogActionListener);
        cancelBtn.addActionListener(dialogActionListener);
    }
}
