package edu.kis.powp.jobs2d.drivers.adapter;


import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverFeature;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickAdapter implements MouseListener {
    private final JPanel jPanel;
    private int x = 0, y = 0;
    private DriverManager driverManager;

    public MouseClickAdapter(JPanel jPanel, DriverManager driverManager) {
        this.jPanel = jPanel;
        this.driverManager = driverManager;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX() - jPanel.getWidth() / 2;
        y = e.getY() - jPanel.getHeight() / 2;

        if (SwingUtilities.isLeftMouseButton(e)) {
            driverManager.getCurrentDriver().operateTo(x, y);
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            driverManager.getCurrentDriver().setPosition(x, y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
