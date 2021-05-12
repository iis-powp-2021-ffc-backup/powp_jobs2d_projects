package edu.kis.powp.jobs2d.visitor;

import java.util.Iterator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class ExecuteCommandVisitor implements ICommandVisitor {
    private Job2dDriver driver;

    public ExecuteCommandVisitor(Job2dDriver driver) {
        this.driver = driver;
    }

    @Override
    public void visit(OperateToCommand operation) {
        operation.execute(driver);
    }

    @Override
    public void visit(SetPositionCommand operation) {
        operation.execute(driver);
    }

    @Override
    public void visit(ICompoundCommand commands) {
        Iterator<DriverCommand> iter = commands.iterator();
        iter.forEachRemaining(command -> command.accept(this));
    }
}