package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.List;

public class MacroDecoratorDriver implements Job2dDriver {

    public Job2dDriver getDriver() {
        return driver;
    }

    private Job2dDriver driver;


    public void setDriver(Job2dDriver driver) {
        this.driver = driver;
    }
    @Override
    public void setPosition(int x, int y) {
        driver.setPosition(x, y);
        MacroRecorder.addCommand(new SetPositionCommand(x, y));
    }
    @Override
    public void operateTo(int x, int y) {
        driver.operateTo(x, y);
        MacroRecorder.addCommand(new OperateToCommand(x, y));
    }
    @Override
    public String toString() {
        return driver.toString();
    }
}
