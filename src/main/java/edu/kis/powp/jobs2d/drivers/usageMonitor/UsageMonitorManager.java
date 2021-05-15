package edu.kis.powp.jobs2d.drivers.usageMonitor;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UsageMonitorManager {
    private static Logger logger = Logger.getLogger("global");
    private static IMonitorDriverDecorator monitor;

    public static void setDriver(Job2dDriver driver)
    {
        monitor = new MonitorDriverDecorator(driver);
    }

    public static Job2dDriver getDriver()
    {
        return monitor;
    }

    public static void printReport()
    {
        if (monitor != null) {
            logger.log(Level.INFO, "Head distance: " + monitor.getHeadDistance());
            logger.log(Level.INFO, "Operation distance: " + monitor.getOperationDistance());
        } else {
            logger.log(Level.INFO, "No driver attached to monitor");
        }
    }
}
