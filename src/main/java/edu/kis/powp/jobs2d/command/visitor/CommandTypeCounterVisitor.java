package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.Iterator;

public class CommandTypeCounterVisitor implements Visitor {

    private long operateToCounter;
    private long setPositionCounter;

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        operateToCounter++;
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        setPositionCounter++;
    }

    public long getOperateToCounter() {
        return operateToCounter;
    }

    public long getSetPositionCounter() {
        return setPositionCounter;
    }

    public String getResults(){
        return this.toString();
    }

    public String toString() {
        return "Operate to: " + operateToCounter + " set position: " + setPositionCounter + " .";
    }

    @Override
    public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {
        Iterator<DriverCommand> itr = iCompoundCommand.iterator();
        while (itr.hasNext())
            itr.next().accept(this);
    }
}
