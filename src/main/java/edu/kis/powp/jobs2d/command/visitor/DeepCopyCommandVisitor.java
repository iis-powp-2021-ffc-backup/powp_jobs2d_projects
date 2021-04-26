package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeepCopyCommandVisitor implements CommandVisitor {

	private DriverCommand command;

	@Override
	public void visit(CompoundCommand command) {
		List<DriverCommand> clonedCommands = new ArrayList<>();
		Iterator<DriverCommand> iterator = command.iterator();
		while(iterator.hasNext()) {
			DriverCommand c = iterator.next();
			c.accept(this);
			clonedCommands.add(this.command);
		}
		this.command = new CompoundCommand(clonedCommands, command.getName());
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
