package edu.kis.powp.jobs2d.features;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.drivers.observer.IPublisher;
import edu.kis.powp.jobs2d.drivers.observer.ISubscriber;

public class DriverFeature implements IPublisher {

    private static DriverManager driverManager = new DriverManager();
    private static Application app;
    private static List<ISubscriber> subscribers = new ArrayList<>();

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    /**
     * Setup jobs2d drivers Plugin and add to application.
     *
     * @param application Application context.
     */
    public static void setupDriverPlugin(Application application) {
        app = application;
        app.addComponentMenu(DriverFeature.class, "Drivers");
    }

    /**
     * Add driver to context, create button in driver menu.
     *
     * @param name   Button name.
     * @param driver Job2dDriver object.
     */
    public static void addDriver(String name, Job2dDriver driver) {
        SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
        app.addComponentMenuElement(DriverFeature.class, name, listener);
    }

    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public ISubscriber[] getSubscribers() {
        return subscribers.toArray(new ISubscriber[0]);
    }

    public void notifyObservers() {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(driverManager.getCurrentDriver().toString());
        }
    }

    public void clearObservers() {
        subscribers.clear();
    }

    /**
     * Update driver info.
     */
    @Deprecated
    public static void updateDriverInfo() {
        app.updateInfo(driverManager.getCurrentDriver().toString());
    }

}
