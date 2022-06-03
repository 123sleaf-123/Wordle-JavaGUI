package Components;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Title:        Components.JStyleLabel.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A distingguised label extending from JLabel class with special design.
 **/
public class JStyleLabel extends JLabel{

    public JStyleLabel() {
        super();
        initLabel();
    }

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
        this.setMinimumSize(new Dimension(400, 400));
        this.setPreferredSize(new Dimension(20, 20));
        this.setHorizontalAlignment(CENTER);
        this.setFont(new Font("Arial Black", Font.BOLD, 30));
    }
}
