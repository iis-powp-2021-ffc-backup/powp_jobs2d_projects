package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.observer.Publisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {
	private Job2dDriver currentDriver = new LoggerDriver();
	private List<Job2dDriver> allDrivers = new ArrayList<>();
	private static Publisher publisher = new Publisher();
	public Publisher getPublisher(){
		return publisher;
	}

	/**
	 * @param driver Set the driver as current.
	 */
	public synchronized void setCurrentDriver(Job2dDriver driver) {
		currentDriver = driver;
		publisher.notifyObservers();
	}

	/**
	 * @return Current driver.
	 */
	public synchronized Job2dDriver getCurrentDriver() {
		return currentDriver;
	}
	public synchronized Job2dDriver getCompositeDriver() {
		CompositeDriver composite =  new CompositeDriver();
		for(Job2dDriver driver:allDrivers){
			composite.add(driver);
		}

		composite.add(currentDriver);
		return composite;
	}

	public List<Job2dDriver> getAllDrivers() {
		return allDrivers;
	}
}
