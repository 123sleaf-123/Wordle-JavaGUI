package Components;

import javax.swing.*;
import java.awt.*;

/**
 * Title:        JStyleButton.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A Button extending from JButton class with special design.
 **/
public class JStyleButton extends JButton {
    public static final int NON = 0, PINK = 1;
    private int colorCode = NON;

    /**
     * Default constructor of special design Button
     */
    public JStyleButton() {
        super();
        initialise();
    }

    /**
     * @param text The text that the button contains.
     */
    public JStyleButton(String text) {
        super(text);
        initialise();
    }


    public JStyleButton(String text, int colorCode) {
        super(text);
        setColorCode(colorCode);
        initialise();
    }

    public void initialise() {
        this.setOpaque(true);
        if (colorCode == 0) this.setBackground(new Color(0, 0, 0));
        if (colorCode == 1) this.setBackground(new Color(255, 0, 204));

        this.setForeground(new Color(255, 255, 255));
        this.setFont(new Font("Arial BLACk", Font.ITALIC, 16));
        this.setPreferredSize(new Dimension(100, 40));
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }
}