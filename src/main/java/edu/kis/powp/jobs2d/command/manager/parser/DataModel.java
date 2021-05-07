package edu.kis.powp.jobs2d.command.manager.parser;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private String name;

    static class Command {
        String name;
        int posX;
        int posY;
    }

    private static final String SET_POSITION_COMMAND = "SetPosition";
    private static final String OPERATE_TO_COMMAND = "OperateTo";

    private List<Command> driverCommands;

    public List<DriverCommand> getDriverCommand() {
        List<DriverCommand> complexCommand = new ArrayList<>();

        driverCommands.forEach(command -> {
            if (command.name.equals(SET_POSITION_COMMAND)) {
                complexCommand.add(new SetPositionCommand(command.posX, command.posY));
            } else if (command.name.equals(OPERATE_TO_COMMAND)) {
                complexCommand.add(new OperateToCommand(command.posX, command.posY));
            }
        });

        return complexCommand;
    }

    public String getDriverCommandName() {
        return name;
    }
}
