package edu.kis.powp.jobs2d.features;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseListener;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

public class MouseControlsFeature {
    private JPanel panel;
    private DriverManager driverManager;

    private final int LEFT_BUTTON = 1;
    private final int RIGHT_BUTTON = 3;

    private final MouseInputListener mouseListener = new MouseInputListener() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            final int halfWidth = panel.getWidth() / 2;
            final int halfHeight = panel.getHeight() / 2;
            final Job2dDriver driver = driverManager.getCurrentDriver();
            if(e.getButton() == LEFT_BUTTON){
                driver.operateTo(e.getX() - halfWidth, e.getY() - halfHeight);
                driver.setPosition(e.getX() - halfWidth, e.getY() - halfHeight);
            }
            else if(e.getButton() == RIGHT_BUTTON){
                driver.setPosition(e.getX() - halfWidth, e.getY() - halfHeight);
            }
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseDragged(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseMoved(java.awt.event.MouseEvent e) {
        }
    };

    public MouseControlsFeature(DriverManager driverManager, JPanel panel) {
        this.panel = panel;
        this.driverManager = driverManager;
    }

    public void setDriverManager(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    private boolean listenerAlreadyAttached() {
        for (MouseListener listener : panel.getMouseListeners()) {
            if (listener == mouseListener) {
                return true;
            }
        }
        return false;
    }

    public void attachListeners() {
        // prevent attaching the same listener twice
        if (this.listenerAlreadyAttached()) return;
        panel.addMouseListener(mouseListener);
    }

    public void clearListeners() {
        panel.removeMouseListener(mouseListener);
    }
}