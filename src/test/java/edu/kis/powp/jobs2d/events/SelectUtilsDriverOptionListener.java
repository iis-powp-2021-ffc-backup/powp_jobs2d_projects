package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SelectUtilsDriverOptionListener implements ActionListener {
	private Job2dDriver driver;
	private DriverManager driverManager;
	public SelectUtilsDriverOptionListener(Job2dDriver driver, DriverManager driverManager) {
		this.driver = driver;
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JCheckBoxMenuItem)e.getSource()).getState())
			driverManager.addUtilsDriver(driver);
		else
			driverManager.removeUtilsDriver(driver);
	}
}
