package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ComplexCommandFactory;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TriangleListener implements ActionListener {

	private DriverManager driverManager;
	ComplexCommandFactory comm = new ComplexCommandFactory();

	public TriangleListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		comm.drawTriangle(-50,-50, 50, 50).execute(driverManager.getCompositeDriver());
	}
}
