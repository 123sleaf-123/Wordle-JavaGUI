package Components;

import javax.swing.*;
import java.awt.*;

/**
 * Title:        VerSpacer.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description A vertical spacer.
 **/
public class VerSpacer extends JPanel {

    private int unit = 2;
    private int perLen = 100;

    /**
     * Default spacer with 2 unit and 100 per(unit)Len.
     */
    public VerSpacer(JPanel owner) {
        initialise();
    }

    /**
     * Spacer with indicated number of unit and 100 per(unit)Len.
     *
     * @param owner
     * @param unit
     */
    public VerSpacer(JPanel owner, int unit) {
        this.unit = unit;
        initialise();
    }

    /**
     * Spacer with indicated number of unit and indicated per(unit)Len.
     *
     * @param owner
     * @param unit
     * @param perLen
     */
    public VerSpacer(JPanel owner, int unit, int perLen) {
        this.unit = unit;
        this.perLen = perLen;
        initialise();
    }

    /**
     * Initialise HorSpacer instance.
     */
    public void initialise() {
        this.setLayout(new GridLayout(unit, 1, 5, perLen));
        for (int i = 0; i < unit; i++) {
            JLabel spacer = new JLabel();
            spacer.setOpaque(false);
            this.add(spacer);
        }
        this.setOpaque(false);
    }
}
