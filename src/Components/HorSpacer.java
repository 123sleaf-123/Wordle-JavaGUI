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
    public HorSpacer() {
        char[] filler = new char[30];
        Arrays.fill(filler, ' ');
        JLabel spacer = new JLabel(String.valueOf(filler));
        this.add(spacer);
    }
}
