package edu.kis.powp.jobs2d.command.figurefactory;

import edu.kis.powp.jobs2d.command.ComplexCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

public class TriangleFigureComplexCommand implements FigureFactory {

    @Override
    public ICompoundCommand createCompoundCommand() {
        return new ComplexCommand.Builder()
                .addCommand(new SetPositionCommand(-120, -120))
                .addCommand(new OperateToCommand(120, -120))
                .addCommand(new OperateToCommand(120, 120))
                .addCommand(new OperateToCommand(-120, -120))
                .build();
    }
}
