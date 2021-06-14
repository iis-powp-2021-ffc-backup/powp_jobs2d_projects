package edu.kis.powp.jobs2d.drivers;

import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.*;

public class MacroDriver implements Job2dDriver {
	private List<DriverCommand> commands = new ArrayList<>();
	
	
	@Override
	public void setPosition(int x, int y) {
		commands.add(new SetPositionCommand(x, y));
	}

	@Override
	public void operateTo(int x, int y) {
		commands.add(new OperateToCommand(x, y));		
	}
	
	public ImmutableCompoundCommand getMacro() {
		return new ImmutableCompoundCommand(commands);
	}
	
	public void clearMacro() {
		commands.clear();
	}
	
}
