package Components;

import javax.swing.*;
import java.util.Arrays;

/**
 * Title:        HorSpacer.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A horizontal spacer.
 **/
public class HorSpacer extends JPanel {
    /**
     * Default Generator, using 40 spaces to control the width.
     */
    public HorSpacer() {
        char[] filler = new char[40];
        Arrays.fill(filler, ' ');
        JLabel spacer = new JLabel(String.valueOf(filler));
        this.add(spacer);
    }

    /**
     * Indicate the number of units of space to control the width.
     * @param unit Number of units of space.
     */
    public HorSpacer(int unit) {
        char[] filler = new char[unit];
        Arrays.fill(filler, ' ');
        JLabel spacer = new JLabel(String.valueOf(filler));
        this.add(spacer);
    }
}
