package edu.kis.powp.jobs2d.drivers.usageMonitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MonitorFeature;
import edu.kis.powp.observer.Subscriber;

public class UsageMonitorUpdater implements Subscriber {
    @Override
    public void update() {
        Job2dDriver driver = DriverFeature.getDriverManager().getCurrentDriver();
        if (driver != MonitorFeature.getDriver()) {
            MonitorFeature.setDriver(driver);
            DriverFeature.getDriverManager().setCurrentDriver(MonitorFeature.getDriver());
        }
    }
}
