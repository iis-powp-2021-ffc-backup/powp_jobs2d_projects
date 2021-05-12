package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CompoundCommand implements ICompoundCommand {

	private String name;
	private List<DriverCommand> driverCommands;

	public CompoundCommand(List<DriverCommand> driverCommands, String name) {
		Objects.requireNonNull(driverCommands);
		Objects.requireNonNull(name);
		this.driverCommands = driverCommands;
		this.name = name;
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
	public CompoundCommand clone() {
		ArrayList<DriverCommand> list = new ArrayList<>();
		for(DriverCommand d : driverCommands) {
			list.add(d.clone());
		}
		return new CompoundCommand(list, name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CompoundCommand that = (CompoundCommand) o;
		if (!name.equals(that.name) || this.driverCommands.size() != that.driverCommands.size())
			return false;
		for(int i=0; i<this.driverCommands.size(); i++) {
			if(!this.driverCommands.get(i).equals(that.driverCommands.get(i)))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public void addCommand(DriverCommand command) {
		Objects.requireNonNull(name);
		driverCommands.add(command);
	}
}