package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.observer.Publisher;

import javax.print.attribute.standard.JobSheets;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {
	private Job2dDriver currentDriver;
	private CompositeDriver compositeDriver = new CompositeDriver();
	private static Publisher publisher = new Publisher();
	public Publisher getPublisher(){
		return publisher;
	}

	/**
	 * @param driver Set the driver as current.
	 */
	public synchronized void setCurrentDriver(Job2dDriver driver) { // should by LineDriverAdapter, but will not work with MacroRecorder
		compositeDriver.remove(currentDriver);
		compositeDriver.add(driver);
		currentDriver = driver;
		publisher.notifyObservers();
	}

	/**
	 * @return Current driver.
	 */
	public synchronized Job2dDriver getCurrentDriver() {
		return compositeDriver;
	}
	public synchronized Job2dDriver getMainDriver() {
		return currentDriver;
	}
	public void addUtilsDriver(Job2dDriver utilsDriver){
		compositeDriver.add(utilsDriver);
	}
	public void removeUtilsDriver(Job2dDriver utilsDriver){
		compositeDriver.remove(utilsDriver);
	}
}
