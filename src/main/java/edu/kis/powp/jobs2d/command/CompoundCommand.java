package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CompoundCommand implements ICompoundCommand {
    private String name = "CompoundCommand";
    private List<DriverCommand> driverCommands;

    public CompoundCommand(List<DriverCommand> driverCommands, String name) {
        this.driverCommands = driverCommands;
        this.name = name;
    }

    public CompoundCommand(List<DriverCommand> driverCommands) {
        this.driverCommands = driverCommands;
    }

    private CompoundCommand() {
    }

    @Override
    public void execute(Job2dDriver driver) {
        driverCommands.forEach((c) -> c.execute(driver));
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return driverCommands.iterator();
    }

    @Override
    public ICompoundCommand clone() {
        CompoundCommand cloneCompoundCommand = new CompoundCommand();
        cloneCompoundCommand.driverCommands = new LinkedList<>();

        this.driverCommands.forEach((e) -> cloneCompoundCommand.driverCommands.add(e.clone()));
        cloneCompoundCommand.name = this.name;
        return cloneCompoundCommand;
    }

    @Override
    public String toString() {
        return name;
    }
}
