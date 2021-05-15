package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.visitor.Visitor;
import edu.kis.powp.jobs2d.drivers.transformation.Point;

/**
 * Implementation of Job2dDriverCommand for setPosition command functionality.
 */
public class SetPositionCommand implements DriverCommand {


    private String name = "SetPosition";
    private final Point point;


    public SetPositionCommand(int posX, int posY) {
        super();
        point = new Point(posX, posY);
    }

    public SetPositionCommand(Point point) {
        super();
        this.point=point;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public void execute(Job2dDriver driver) {
        driver.setPosition(point.x, point.y);
    }

    @Override
    public DriverCommand clone() {
        return new OperateToCommand(point.x, point.y);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitSetPositionCommand(this);
    }

}
