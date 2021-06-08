package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;
import static java.lang.Math.pow;

public class UsageMonitoringDriver  implements Job2dDriver {

	private LineDriverAdapter lineDriverAdapter;
	private static final Logger LOGGER = Logger.getLogger("global");
	private int headDistance = 0;
	private int opDistance = 0;


//	public UsageMonitoringDriver(DrawPanelController drawController, ILine line, String name) {
//		super(drawController, line, name);
//	}

	public UsageMonitoringDriver(LineDriverAdapter lineDriverAdapter) {
		this.lineDriverAdapter = lineDriverAdapter;
	}

	public void setPosition(int x, int y) {
		headDistance += distanceBetweenPoints(lineDriverAdapter.getStartX(), lineDriverAdapter.getStartY(), x, y);
		LOGGER.info("Head distance: " + headDistance + " units" + " " + "Op.distance: " + opDistance + " units");
//		lineDriverAdapter.setPosition(x, y);
	}


	public void operateTo(int x, int y) {
		opDistance += distanceBetweenPoints(lineDriverAdapter.getStartX(), lineDriverAdapter.getStartY(), x, y);
		headDistance += distanceBetweenPoints(lineDriverAdapter.getStartX(), lineDriverAdapter.getStartY(), x, y);
//		lineDriverAdapter.operateTo(x, y);
		LOGGER.info("Op.distance: " + opDistance + " units");
	}

	public void setLineDriverAdapter(LineDriverAdapter lineDriverAdapter) {
		this.lineDriverAdapter = lineDriverAdapter;
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
