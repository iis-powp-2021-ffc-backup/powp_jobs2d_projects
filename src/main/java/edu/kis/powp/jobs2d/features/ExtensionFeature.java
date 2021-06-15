package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectExtensionMenuOptionListener;

public class ExtensionFeature implements Feature {
    private static DriverManager driverManager;
    private static Application app;
    
    public static DriverManager getDriverManager() {
        return driverManager;
    }
    
    public ExtensionFeature(Application application, DriverManager manager) {
        app = application;
        driverManager = manager;
    }

    @Override
    public void setup() {
        app.addComponentMenu(ExtensionFeature.class, "Extensions");
    }
    
    public static void addDriver(String name, Job2dDriver driver) {
        SelectExtensionMenuOptionListener listener = new SelectExtensionMenuOptionListener(driver, driverManager);
        app.addComponentMenuElementWithCheckBox(ExtensionFeature.class, name, listener, false);
    }
}
