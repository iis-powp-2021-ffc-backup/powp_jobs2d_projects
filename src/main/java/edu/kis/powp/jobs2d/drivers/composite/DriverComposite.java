package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Base composite of drivers.
 */
public class DriverComposite implements IDriverComposite {
    private List<Job2dDriver> children = new ArrayList<>();

    @Override
    public void setPosition(int x, int y) {
        for (Job2dDriver driver : children) {
            driver.setPosition(x, y);
        }
    }

    @Override
    public void operateTo(int x, int y) {
        for (Job2dDriver driver : children) {
            driver.operateTo(x, y);
        }
    }

    @Override
    public void add(Job2dDriver item) {
        children.add(item);
    }
    
    @Override
    public void remove(Job2dDriver item) {
    	children.remove(item);
    }
}
