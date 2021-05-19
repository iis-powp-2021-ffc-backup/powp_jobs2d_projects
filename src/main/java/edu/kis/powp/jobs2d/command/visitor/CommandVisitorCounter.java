package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class CommandVisitorCounter implements Visitor {

    private int counter = 0;

    public int getAllCommandsCounter() {
        return counter;
    }

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        counter++;
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        counter++;
    }

    @Override
    public void visitICompoundCommand(ICompoundCommand compoundCommand) {
        Iterator<DriverCommand> iterator = compoundCommand.iterator();
        while (iterator.hasNext())
        {
            DriverCommand driverCommand = iterator.next();
            driverCommand.accept(this);
        }
    }
}