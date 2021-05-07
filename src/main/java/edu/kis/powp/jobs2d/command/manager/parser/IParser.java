package edu.kis.powp.jobs2d.command.manager.parser;

import edu.kis.powp.jobs2d.command.DriverCommand;

import java.io.IOException;

public interface IParser {
    DataModel parseFromJson(String data);

    String parseToJson(DriverCommand driverCommand) throws IOException;
}
