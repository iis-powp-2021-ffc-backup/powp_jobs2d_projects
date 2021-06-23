package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.List;

public class MacroDecoratorDriver implements Job2dDriver {
	@Override
	public void setPosition(int x, int y) {
		MacroRecorder.addCommand(new SetPositionCommand(x, y));
	}
	@Override
	public void operateTo(int x, int y) {
		MacroRecorder.addCommand(new OperateToCommand(x, y));
	}
	@Override
	public String toString() {
		return "Macro driver";
	}
}
