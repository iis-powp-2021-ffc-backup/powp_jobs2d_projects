package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.Visitor;

/**
 * Implementation of Job2dDriverCommand for operateTo command functionality.
 */
public class OperateToCommand implements DriverCommand {

    private int posX, posY;
    private String name = "OperateTo";

    public OperateToCommand(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void execute(Job2dDriver driver) {
        driver.operateTo(posX, posY);
    }

	@Override
	public DriverCommand clone() {
		return new OperateToCommand(this.posX,this.posY);
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visitOperateToCommand(this);
    }
}
