package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.jobs2d.drivers.composite.IDriverComposite;
import edu.kis.powp.observer.Publisher;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {

	private IDriverComposite driverComposite = new DriverComposite();
    private Job2dDriver currentDriver = new LoggerDriver();
    private Publisher changePublisher = new Publisher();

    /**
     * @param driver Set the driver as current.
     */
    public synchronized void setCurrentDriver(Job2dDriver driver) {
        driverComposite.remove(currentDriver);
        driverComposite.add(driver);
    	currentDriver = driver;
        changePublisher.notifyObservers();
    }

    public synchronized void addExtension(Job2dDriver extension) {
    	driverComposite.add(extension);
    }
    
    public synchronized void removeExtension(Job2dDriver extension) {
    	driverComposite.remove(extension);
    }
    
    /**
     * @return Current driver (that is composite driver of main current driver and extensions).
     */
    public synchronized Job2dDriver getCurrentDriver() {
        return currentDriver;
    }

    public synchronized Publisher getPublisher() {
        return changePublisher;
    }
}
