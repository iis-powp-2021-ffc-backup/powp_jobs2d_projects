package edu.kis.powp.jobs2d.drivers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.Job2dDriver;

public class SelectExtensionMenuOptionListener implements ActionListener {
    private final DriverManager driverManager;
    private Job2dDriver driver = null;
    private boolean state = false;
    
    public SelectExtensionMenuOptionListener(Job2dDriver driver, DriverManager driverManager) {
        this.driver = driver;
        this.driverManager = driverManager;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(state) {
            driverManager.removeExtension(driver);
        }
        else {
            driverManager.addExtension(driver);
        }
        state = !state;
    }
}
