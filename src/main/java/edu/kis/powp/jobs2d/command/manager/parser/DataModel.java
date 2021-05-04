package edu.kis.powp.jobs2d.command.manager.parser;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private static String SET_POSITION_COMMAND = "SetPositionCommand";
    private static String OPERATE_TO_COMMAND = "OperateToCommand";

    private String name;
    static class Command {
        String operation;
        int posX;
        int posY;
    }
    private List<Command> commands;

    public List<DriverCommand> getDriverCommand() {
        List<DriverCommand> complexCommand = new ArrayList<>();

        commands.forEach(command -> {
            if(command.operation.equals(SET_POSITION_COMMAND)){
                complexCommand.add(new SetPositionCommand(command.posX, command.posY));
            } else if (command.operation.equals(OPERATE_TO_COMMAND)) {
                complexCommand.add(new OperateToCommand(command.posX, command.posY));
            }
        });

        return complexCommand;
    }

    public String getDriverCommandName() {
        return name;
    }
}
