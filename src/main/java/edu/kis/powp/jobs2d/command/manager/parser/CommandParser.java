package edu.kis.powp.jobs2d.command.manager.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.kis.powp.jobs2d.command.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandParser implements IDriverCommandParser {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public CommandDataModel parseFromString(String data) {
        return gson.fromJson(data, CommandDataModel.class);
    }

    @Override
    public String parseToString(DriverCommand driverCommand) {
        if (driverCommand instanceof OperateToCommand || driverCommand instanceof SetPositionCommand) {
            return gson.toJson(driverCommand);
        }

        List<DriverCommand> driverCommandList = new ArrayList<>();
        flatList(driverCommand, driverCommandList);
        CompoundCommand compoundCommand = new CompoundCommand(driverCommandList);
        return gson.toJson(compoundCommand);
    }

    List<DriverCommand> getList(DriverCommand driverCommand) {
        List<DriverCommand> driverCommands = new ArrayList<>();
        if (driverCommand instanceof OperateToCommand || driverCommand instanceof SetPositionCommand) {
            driverCommands.add(driverCommand);
            return driverCommands;
        }

        flatList(driverCommand, driverCommands);
        return driverCommands;
    }

    private void flatList(DriverCommand driverCommand, List<DriverCommand> driverCommands) {
        Iterator<DriverCommand> iterator;
        if (driverCommand instanceof CompoundCommand)
            iterator = ((CompoundCommand) driverCommand).iterator();
        else
            iterator = ((ComplexCommand) driverCommand).iterator();

        while (iterator.hasNext()) {
            DriverCommand command = iterator.next();
            driverCommands.addAll(this.getList(command));
        }
    }


}
