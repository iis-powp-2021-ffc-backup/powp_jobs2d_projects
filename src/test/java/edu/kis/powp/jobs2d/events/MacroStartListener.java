package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.MacroDecoratorDriver;
import edu.kis.powp.jobs2d.drivers.MacroRecorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MacroStartListener implements ActionListener {
    private DriverManager driverManager;
    public MacroStartListener(DriverManager driverManager) {
        this.driverManager = driverManager;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MacroDecoratorDriver mDriver = new MacroDecoratorDriver();
        mDriver.setDriver(driverManager.getCurrentDriver());
        driverManager.setCurrentDriver(mDriver);
        MacroRecorder.startRecording();
    }
}
