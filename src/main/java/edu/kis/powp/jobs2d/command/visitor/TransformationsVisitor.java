package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.drivers.transformation.Point;
import edu.kis.powp.jobs2d.drivers.transformation.PointTransformation;

import java.util.Iterator;

public class TransformationsVisitor implements Visitor {

    private final ComplexCommand.Builder complexCommandBuilder = new ComplexCommand.Builder();
    private final PointTransformation transformation;

    public TransformationsVisitor(PointTransformation transformation) {
        this.transformation = transformation;
    }

    public ComplexCommand getTransformedComplexCommand() {
        return complexCommandBuilder.build();
    }

    @Override
    public void visitOperateToCommand(OperateToCommand operateToCommand) {
        Point point = operateToCommand.getPoint();
        Point newPoint = transformation.transform(point);
        complexCommandBuilder.addCommand(new OperateToCommand(newPoint.x, newPoint.y));
    }

    @Override
    public void visitSetPositionCommand(SetPositionCommand setPositionCommand) {
        Point point = setPositionCommand.getPoint();
        Point newPoint = transformation.transform(point);
        complexCommandBuilder.addCommand(new SetPositionCommand(newPoint.x, newPoint.y));
    }

    @Override
    public void visitICompoundCommand(ICompoundCommand iCompoundCommand) {
        Iterator<DriverCommand> itr = iCompoundCommand.iterator();
        while (itr.hasNext()) {
            itr.next().accept(this);
        }
    }
}
