package edu.kis.powp.jobs2d.command.history;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;

public class HistoryCommandObject {
    private String name,date;
    private ICompoundCommand comm;

    public HistoryCommandObject(String date, String commandName, ICompoundCommand command) {
        this.date = date;
        this.name = commandName;
        this.comm = command;
    }

    public ICompoundCommand getComm() {
        return comm;
    }

    @Override
    public String toString() {
        return "At "+ date + " used " + name;
    }
}