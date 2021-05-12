package edu.kis.powp.legacy.drawer;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.UsageMonitoringDriver;


public class TestDistanceMonitor {
	private static final String TEST_ERROR = "Test failed!";

	public static void main(String[] args) {
		DrawPanelController controller = new DrawPanelController();
		LineDriverAdapter lineDriverAdapter = new LineDriverAdapter(controller, LineFactory.getBasicLine(), "basic");
		UsageMonitoringDriver driver = new UsageMonitoringDriver(lineDriverAdapter);

		driver.operateTo(0, 10);

		customAssert(driver.getHeadDistance() == 10);
		customAssert(driver.getOpDistance() == 10);

		driver.setPosition(0, 0);
		customAssert(driver.getHeadDistance() == 20);
		customAssert(driver.getOpDistance() == 10);

		driver.operateTo(0, -10);
		customAssert(driver.getHeadDistance() == 30);
		customAssert(driver.getOpDistance() == 20);
		System.out.println("Head distance: " + driver.getHeadDistance());
		System.out.println("Op. distance: " + driver.getOpDistance());
	}

	private static void customAssert(boolean b) {
		if (!b)
			System.err.println("\n" + TEST_ERROR + "\n");
	}
}
