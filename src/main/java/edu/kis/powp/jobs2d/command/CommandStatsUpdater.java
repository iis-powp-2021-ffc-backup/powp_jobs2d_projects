package edu.kis.powp.jobs2d.command;

import java.util.logging.Logger;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandTypeCounterVisitor;
import edu.kis.powp.observer.Subscriber;

public class CommandStatsUpdater implements Subscriber {
  private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private DriverCommandManager driverCommandManager;

  private long operateToCounterSum = 0;
  private long setPositionCounterSum = 0;
  private long commandsLoaded = 0;

  public CommandStatsUpdater(DriverCommandManager driverCommandManager) {
      this.driverCommandManager = driverCommandManager;
  }

  public void update() {
    DriverCommand command = driverCommandManager.getCurrentCommand();
    CommandTypeCounterVisitor countingVisitor = new CommandTypeCounterVisitor();
    command.accept(countingVisitor);
    operateToCounterSum += countingVisitor.getOperateToCounter();
    setPositionCounterSum += countingVisitor.getSetPositionCounter();
    commandsLoaded += 1;
    logger.info("operateTo counter sum: " + operateToCounterSum);
    logger.info("setPosition counter sum: " + setPositionCounterSum);
    logger.info("total complex commands loaded: " + commandsLoaded);
    logger.info("total commands loaded: " + (operateToCounterSum + setPositionCounterSum));
  }
}
