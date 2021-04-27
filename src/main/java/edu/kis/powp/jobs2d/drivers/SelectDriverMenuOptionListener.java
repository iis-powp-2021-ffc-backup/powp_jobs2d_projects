package edu.kis.powp.jobs2d.drivers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.observer.IPublisher;
import edu.kis.powp.jobs2d.drivers.observer.ISubscriber;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class SelectDriverMenuOptionListener implements ActionListener, IPublisher {
    private DriverManager driverManager;
    private Job2dDriver driver = null;
    private List<ISubscriber> subscribers = new ArrayList<>();

    public SelectDriverMenuOptionListener(Job2dDriver driver, DriverManager driverManager) {
        this.driverManager = driverManager;
        this.driver = driver;
    }
    
    /**
     * Add a subscriber to the pool
     */
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Return all active subscribers
     */

    public ISubscriber[] getSubscribers() {
        return subscribers.toArray(new ISubscriber[0]);
    }

    /**
     * Notify all active subscribers
     */

    public void notifyObservers() {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    /**
     * Remove all subscribers from the pool
     */

    public void clearObservers() {
        subscribers.clear();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        driverManager.setCurrentDriver(driver);
        // DriverFeature.updateDriverInfo();
        notifyObservers();
    }
}
