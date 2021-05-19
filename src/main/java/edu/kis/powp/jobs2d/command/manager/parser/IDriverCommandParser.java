package edu.kis.powp.jobs2d.command.manager.parser;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.io.IOException;

public interface IDriverCommandParser {
    CommandDataModel parseFromFile(String data);

    String parseToFile(DriverCommand driverCommand) throws IOException;
}
