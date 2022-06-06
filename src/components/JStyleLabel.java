package components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Title:        components.JStyleLabel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A label extending from JLabel class with special design.
 **/
public class JStyleLabel extends JLabel {

    /**
     * Default Generator method of JStyleLabel
     */
    public JStyleLabel() {
        super();
        initLabel();
    }

    /**
     * Generator method of JStyleLabel with initialisation of content text
     * @param text content text
     */
    public JStyleLabel(String text) {
        super(text);
        initLabel();
    }

    /**
     * Initialisation of JStyleLabel,
     */
    public void initLabel() {
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setBorder(LineBorder.createBlackLineBorder());
        this.setPreferredSize(new Dimension(20, 20));
        this.setHorizontalAlignment(CENTER);
        this.setFont(new Font("Arial Black", Font.BOLD, 30));
    }
}
