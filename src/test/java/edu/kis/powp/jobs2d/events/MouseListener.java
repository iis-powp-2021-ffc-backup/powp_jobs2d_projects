package edu.kis.powp.jobs2d.events;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.features.DriverFeature;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener {

    public void mouseClickConverter(Application application) {

        JPanel jPanel = application.getFreePanel();
        jPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //Left click
                if (SwingUtilities.isLeftMouseButton(e)) {
                    DriverFeature.getDriverManager()
                            .getCurrentDriver()
                            .operateTo(e.getX() - jPanel.getWidth() / 2, e.getY() - jPanel.getHeight() / 2);
                    System.out.println("New position x= " + e.getX());
                }

                //Right click
                if (SwingUtilities.isRightMouseButton(e)) {
                    DriverFeature.getDriverManager()
                            .getCurrentDriver()
                            .setPosition(e.getX() - jPanel.getWidth() / 2, e.getY() - jPanel.getHeight() / 2);
                    System.out.println("Position saved x= " + e.getX());
                }
            }
        });
    }

}
