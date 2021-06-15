package edu.kis.powp.jobs2d.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;

public final class ImmutableCompoundCommand implements ICompoundCommand {

    private String name;
    private List<DriverCommand> commandsList;

    public Iterator<DriverCommand> iterator() {
        return commandsList.iterator();
    }

    public ImmutableCompoundCommand(List<DriverCommand> driverCommands, String str) {
        name = str;
        commandsList = new ArrayList<>();

        for (DriverCommand command : driverCommands) {
            commandsList.add(command.clone());
        }
    }

    public ImmutableCompoundCommand(List<DriverCommand> commands) {
        commandsList = new ArrayList<>();

        for (DriverCommand command : commands) {
            commandsList.add(command.clone());
        }
    }

    public ImmutableCompoundCommand(ICompoundCommand compoundCommand, String str) {
        super();

        name = str;
        commandsList = new ArrayList<>();

        Iterator<DriverCommand> iterator = compoundCommand.iterator();
        while(iterator.hasNext()){
            commandsList.add(iterator.next().clone());
        }
    }

    public ImmutableCompoundCommand clone() {

        ImmutableCompoundCommand command;

        try {
            command = (ImmutableCompoundCommand) super.clone();
            command.name = this.name;

            command.commandsList = new ArrayList<>();
            for (DriverCommand cmd : this.commandsList) {
                command.commandsList.add(cmd.clone());
            }

        } catch (CloneNotSupportedException e) {
            command = new ImmutableCompoundCommand(this, this.name);
        }

        return command;
    }

    public void execute(Job2dDriver driver) {

        Iterator<DriverCommand> iterator = this.iterator();
        while(iterator.hasNext()){
            iterator.next().execute(driver);
        }
    }
}
