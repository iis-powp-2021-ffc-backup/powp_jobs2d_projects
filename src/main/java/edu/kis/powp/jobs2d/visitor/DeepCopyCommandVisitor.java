package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeepCopyCommandVisitor implements ICommandVisitor {

    private DriverCommand command;

    @Override
    public void visit(ICompoundCommand command) {
        List<DriverCommand> clonedCommands = new ArrayList<>();
        Iterator<DriverCommand> iterator = command.iterator();
        while(iterator.hasNext()) {
            DriverCommand c = iterator.next();
            c.accept(this);
            clonedCommands.add(this.command);
        }
        this.command = new CompoundCommand(clonedCommands, command.toString());
    }

    @Override
    public void visit(OperateToCommand command) {
        this.command = new OperateToCommand(command.getPosX(), command.getPosY());
    }

    @Override
    public void visit(SetPositionCommand command) {
        this.command = new SetPositionCommand(command.getPosX(), command.getPosY());
    }

    public DriverCommand getCommand() {
        return command;
    }
}