package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.observer.CheckboxAction;

import javax.swing.*;
import java.awt.*;

public class MouseFeature implements Feature {

    private static Application application;
    private static CheckboxAction enableMouseAction;

    public MouseFeature(Application application){
        this.application = application;
    }

    @Override
    public void setup() {
        JPanel panel = application.getFreePanel();

        JCheckBox mouseCheckbox = new JCheckBox("Enable mouse");
        mouseCheckbox.setToolTipText("Enable manual drawing with mouse.");
        mouseCheckbox.setBounds(0,0,100,30);
        mouseCheckbox.setCursor(new Cursor(12));

        panel.add(mouseCheckbox, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NORTHWEST, new Insets(0, 0, 0, 0), 0, 0));

        enableMouseAction = new CheckboxAction("Enable mouse");
        mouseCheckbox.setAction(enableMouseAction);
    }

    public static CheckboxAction getEnableMouseAction() {
        return enableMouseAction;
    }
}