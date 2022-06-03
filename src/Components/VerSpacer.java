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
    /**
     * Default spacer with 2 unit and 100 per(unit)Len
     */
    public VerSpacer(JPanel owner) {
        int unit = 2;
        this.setLayout(new GridLayout(unit, 1, 5, 100));
        for (int i = 0; i < unit; i++) {
            this.add(new Label());
        }
    }

    /**
     * Spacer with indicated number of unit and 100 per(unit)Len
     *
     * @param owner
     * @param unit
     */
    public VerSpacer(JPanel owner, int unit) {
        this.setLayout(new GridLayout(unit, 1, 5, 100));
        for (int i = 0; i < unit; i++) {
            this.add(new Label());
        }
    }

    /**
     * Spacer with indicated number of unit and indicated per(unit)Len
     *
     * @param owner
     * @param unit
     * @param perLen
     */
    public VerSpacer(JPanel owner, int unit, int perLen) {
        this.setLayout(new GridLayout(unit, 1, 5, perLen));
        for (int i = 0; i < unit; i++) {
            this.add(new Label());
        }
    }
}
