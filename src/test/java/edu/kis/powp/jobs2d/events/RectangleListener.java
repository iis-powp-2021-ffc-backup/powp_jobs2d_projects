package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.ComplexCommandFactory;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.magicpresets.FiguresJoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RectangleListener implements ActionListener {

	private DriverManager driverManager;
	ComplexCommandFactory comm = new ComplexCommandFactory();

	public RectangleListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		comm.drawRectangle(0,0, 50, 25).execute(driverManager.getCompositeDriver());
	}
}
