package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.util.logging.Logger;
import static java.lang.Math.pow;

public class UsageMonitoringDriver  implements Job2dDriver {

	private static final Logger LOGGER = Logger.getLogger("global");
	private int headDistance = 0;
	private int opDistance = 0;
	private int startX = 0;
	private int startY = 0;

	public UsageMonitoringDriver() {

	}

	public void setPosition(int x, int y) {
		headDistance += distanceBetweenPoints(startX, startY, x, y);
		LOGGER.info("Head distance: " + headDistance + " units" + " " + "Op.distance: " + opDistance + " units");
		startX = x;
		startY = y;
	}


	public void operateTo(int x, int y) {
		opDistance += distanceBetweenPoints(startX, startY, x, y);
		headDistance += distanceBetweenPoints(startX, startY, x, y);
		LOGGER.info("Op.distance: " + opDistance + " units");
		startX = x;
		startY = y;
	}


	public int getHeadDistance() {
		return headDistance;
	}

	public int getOpDistance() {
		return opDistance;
	}

	private int distanceBetweenPoints(int x1, int y1, int x2, int y2) {
		return (int) Math.ceil(Math.sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2)));
	}
	public Logger getLogger(){
		return LOGGER;
	}

}
