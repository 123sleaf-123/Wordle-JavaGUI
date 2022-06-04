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

    public void initialise() {
        this.setOpaque(true);
        this.setBackground(new Color(255, 0, 204));
        this.setFont(new Font("Arial BLACk", Font.ITALIC, 16));
        this.setForeground(new Color(255, 255, 255));
    }
}
