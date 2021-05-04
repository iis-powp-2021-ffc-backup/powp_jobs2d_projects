package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.Visitor;

/**
 * Implementation of Job2dDriverCommand for setPosition command functionality.
 */
public class SetPositionCommand implements DriverCommand {

    private int posX, posY;
    private String name = "SetPosition";

    public SetPositionCommand(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void execute(Job2dDriver driver) {
        driver.setPosition(posX, posY);
    }

	@Override
	public DriverCommand clone() {
		return new OperateToCommand(this.posX,this.posY);
	}

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSetPositionCommand(this);
    }

}
