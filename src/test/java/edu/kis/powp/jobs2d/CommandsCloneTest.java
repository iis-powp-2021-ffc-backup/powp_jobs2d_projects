package edu.kis.powp.jobs2d;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandsCloneTest {

	@Test
	public void operateToCommandCloneEqualityTest() {
		OperateToCommand command = new OperateToCommand(1, 2);
		OperateToCommand clone = command.clone();

		Assert.assertNotSame(command, clone);
		Assert.assertEquals(command, clone);
	}

	@Test
	public void operateToCommandCloneNotInterfereTest() {
		OperateToCommand command = new OperateToCommand(1, 2);
		OperateToCommand clone = command.clone();

		clone.setPosX(3);
		clone.setPosY(4);

		Assert.assertEquals(command.getPosX(), 1);
		Assert.assertEquals(command.getPosY(), 2);
	}

	@Test
	public void setPositionCommandCloneEqualityTest() {
		SetPositionCommand command = new SetPositionCommand(1, 2);
		SetPositionCommand clone = command.clone();

		Assert.assertNotSame(command, clone);
		Assert.assertEquals(command, clone);
	}

	@Test
	public void setPositionCommandCloneNotInterfereTest() {
		SetPositionCommand command = new SetPositionCommand(1, 2);
		SetPositionCommand clone = command.clone();

		clone.setPosX(3);
		clone.setPosY(4);

		Assert.assertEquals(command.getPosX(), 1);
		Assert.assertEquals(command.getPosY(), 2);
	}

	@Test
	public void compoundCommandCloneEqualityTest() {
		List<DriverCommand> commands = createCommandsList();
		CompoundCommand command = new CompoundCommand(commands, "command");
		CompoundCommand clone = command.clone();

		Assert.assertNotSame(command, clone);
		Assert.assertEquals(command, clone);
	}

	@Test
	public void compoundCommandCloneNotInterfereTest() {
		List<DriverCommand> commandsList = createCommandsList();
		CompoundCommand command = new CompoundCommand(commandsList, "command");
		CompoundCommand clone = command.clone();

		clone.addCommand(new OperateToCommand(0, 0));

		List<DriverCommand> currentCommandsList = new ArrayList<>();
		command.iterator().forEachRemaining(currentCommandsList::add);
		Assert.assertEquals(commandsList, currentCommandsList);
	}

	@Test
	public void nestedCompoundCommandCloneEqualityTest() {
		CompoundCommand commandTop = new CompoundCommand(createCommandsList(), "command");
		CompoundCommand commandMiddle = new CompoundCommand(createCommandsList(), "command");
		CompoundCommand commandBottom1 = new CompoundCommand(createCommandsList(), "command");
		CompoundCommand commandBottom2 = new CompoundCommand(createCommandsList(), "command");

		commandTop.addCommand(commandMiddle);
		commandMiddle.addCommand(commandBottom1);
		commandMiddle.addCommand(commandBottom2);
		CompoundCommand cloneTop = commandTop.clone();

		Assert.assertNotSame(commandTop, cloneTop);
		Assert.assertEquals(commandTop, cloneTop);
	}

	@Test
	public void nestedCompoundCommandCloneNotInterfereTest() {
		CompoundCommand commandTop = new CompoundCommand(new ArrayList<>(), "command");
		CompoundCommand commandMiddle = new CompoundCommand(new ArrayList<>(), "command");
		CompoundCommand commandBottom = new CompoundCommand(new ArrayList<>(), "command");
		OperateToCommand operateCommand = new OperateToCommand(1, 2);

		commandTop.addCommand(commandMiddle);
		commandMiddle.addCommand(commandBottom);
		commandBottom.addCommand(operateCommand);
		CompoundCommand cloneTop = commandTop.clone();

		operateCommand.setPosX(3);

		// Extract operate command from cloned object to make sure it hasn't changed
		CompoundCommand cloneMiddle = (CompoundCommand) cloneTop.iterator().next();
		CompoundCommand cloneBottom = (CompoundCommand) cloneMiddle.iterator().next();
		OperateToCommand cloneOperate = (OperateToCommand) cloneBottom.iterator().next();
		Assert.assertEquals(cloneOperate.getPosX(), 1);
	}

	private List<DriverCommand> createCommandsList(){
		List<DriverCommand> commands = new ArrayList<>();
		commands.add(new SetPositionCommand(1, 2));
		commands.add(new OperateToCommand(3, 4));
		commands.add(new OperateToCommand(5, 6));
		commands.add(new OperateToCommand(7, 8));
		commands.add(new OperateToCommand(9, 10));
		return commands;
	}
}