package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandTypeCounterVisitor;

public class SelectCurrentCommandCounter implements ActionListener {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private DriverCommandManager driverCommandManager;

    public SelectCurrentCommandCounter(DriverCommandManager driverCommandManager) {
        this.driverCommandManager = driverCommandManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommand command = driverCommandManager.getCurrentCommand();
        CommandTypeCounterVisitor countingVisitor = new CommandTypeCounterVisitor();
        command.accept(countingVisitor);
        logger.info(countingVisitor.getResults());
    }

}
