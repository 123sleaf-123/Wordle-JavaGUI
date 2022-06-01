package Components;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
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
        this.setBackground(Color.YELLOW);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
//        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
}
