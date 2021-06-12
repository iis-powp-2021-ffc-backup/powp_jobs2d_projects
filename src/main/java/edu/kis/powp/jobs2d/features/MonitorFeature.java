package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.usageMonitor.IMonitorDriverDecorator;
import edu.kis.powp.jobs2d.drivers.usageMonitor.MonitorDriverDecorator;
import edu.kis.powp.jobs2d.drivers.usageMonitor.UsageMonitorUpdater;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MonitorFeature implements Feature {
    private static Logger logger = Logger.getLogger("global");
    private static IMonitorDriverDecorator usageMonitor = null;

    @Override
    public void setup() {
        UsageMonitorUpdater usageSubscriber = new UsageMonitorUpdater();
        DriverFeature.getDriverManager().getPublisher().addSubscriber(usageSubscriber);
    }

    public static IMonitorDriverDecorator getDriver() {
        return usageMonitor;
    }

    public static void setDriver(Job2dDriver driver) {
        usageMonitor = new MonitorDriverDecorator(driver);
    }

    public static void printReport()
    {
        if (usageMonitor != null) {
            logger.log(Level.INFO, "Head distance: " + usageMonitor.getHeadDistance());
            logger.log(Level.INFO, "Operation distance: " + usageMonitor.getOperationDistance());
        } else {
            logger.log(Level.INFO, "No driver attached to monitor");
        }
    }
}
