package edu.kis.powp.jobs2d.observer;

import java.util.logging.Logger;

public class MouseControlLoggerObserver implements ICheckBoxObserver {
    Logger logger = Logger.getLogger("global");

    @Override
    public void onEnabled() {
        this.logger.info("Mouse control enabled!");
    }

    @Override
    public void onDisabled() {
        this.logger.info("Mouse control disabled!");
    }
}
