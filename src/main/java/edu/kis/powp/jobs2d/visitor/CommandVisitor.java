package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public interface CommandVisitor {
	void visit(CompoundCommand command);
	void visit(OperateToCommand command);
	void visit(SetPositionCommand command);
}
