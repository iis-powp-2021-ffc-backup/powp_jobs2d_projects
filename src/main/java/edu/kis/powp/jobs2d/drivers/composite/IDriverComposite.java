package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;

/**
 * Represents a hierarchy of drivers.
 */
public interface IDriverComposite extends Job2dDriver {
    void add(Job2dDriver item);
    void remove(Job2dDriver item);
}
