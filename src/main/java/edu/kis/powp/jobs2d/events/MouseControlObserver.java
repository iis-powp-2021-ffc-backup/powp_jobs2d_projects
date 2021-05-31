package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.MouseControlsFeature;

import javax.swing.*;

public class MouseControlObserver implements ICheckBoxObserver {
    private MouseControlsFeature feature;
    private DriverManager manager;
    private JPanel panel;

    public MouseControlObserver(DriverManager manager, JPanel panel) {
        this.panel = panel;
        this.manager = manager;
        this.feature = new MouseControlsFeature(manager, panel);
    }

    @Override
    public void onEnabled() {
        feature.attachListeners();
    }

    @Override
    public void onDisabled() {
        feature.clearListeners();
    }
}
