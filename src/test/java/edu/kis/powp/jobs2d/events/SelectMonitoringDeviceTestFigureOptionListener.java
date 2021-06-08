package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.UsageMonitoringDriver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMonitoringDeviceTestFigureOptionListener implements ActionListener {
	private final DriverManager driverManager;
	private final int TESTING_OP_DISTANCE = 200;
	private final int TESTING_HEAD_DISTANCE = 400;

	private int initOpDistance = 0;
	private int initHeadDistance = 0;

	public SelectMonitoringDeviceTestFigureOptionListener(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Job2dDriver driver = driverManager.getCompositeDriver();
		driver.setPosition(0, 0);
		if (driverManager.getAllDrivers() instanceof UsageMonitoringDriver) {
			initOpDistance = ((UsageMonitoringDriver) driver).getOpDistance();
			initHeadDistance = ((UsageMonitoringDriver) driver).getHeadDistance();
		}
		driver.setPosition(100, 0);
		driver.operateTo(100, 50);
		driver.operateTo(150, 50);
		driver.operateTo(150, 0);
		driver.operateTo(100, 0);
		driver.setPosition(0, 0);

		if (driver instanceof UsageMonitoringDriver) {
			UsageMonitoringDriver usageMonitoringDriver = (UsageMonitoringDriver) driver;
			if (usageMonitoringDriver.getOpDistance() - initOpDistance != TESTING_OP_DISTANCE ||
					usageMonitoringDriver.getHeadDistance() - initHeadDistance != TESTING_HEAD_DISTANCE) {
				usageMonitoringDriver.getLogger().warning("Usage monitoring test failed!");
			} else {
				usageMonitoringDriver.getLogger().info("Usage monitoring test passed!");
			}
		}

	}
}
