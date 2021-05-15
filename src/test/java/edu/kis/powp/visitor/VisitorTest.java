package edu.kis.powp.visitor;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.CommandTypeCounterVisitor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class VisitorTest {

    @Test
    public void testEmpty() {
        CommandTypeCounterVisitor visitor = new CommandTypeCounterVisitor();
        Assert.assertEquals(visitor.getOperateToCounter(), 0);
        Assert.assertEquals(visitor.getSetPositionCounter(), 0);
    }

    @Test
    public void testSingleCommandType() {
        int counterActual = 10;
        CommandTypeCounterVisitor countingVisitor = new CommandTypeCounterVisitor();

        for (int i = 0; i < counterActual; i++)
            new OperateToCommand((int) (Math.random() * 10), (int) (Math.random() * 10)).accept(countingVisitor);

        Assert.assertEquals(countingVisitor.getOperateToCounter(), counterActual);
        Assert.assertEquals(countingVisitor.getSetPositionCounter(), 0);
    }

    @Test
    public void testManyCommandTypes() {
        int setPositionActual = 10;
        int operateToActual = 14;
        CommandTypeCounterVisitor visitor = new CommandTypeCounterVisitor();

        for (int i = 0; i < setPositionActual; i++)
            new SetPositionCommand((int) (Math.random() * 10), (int) (Math.random() * 10)).accept(visitor);

        for (int i = 0; i < operateToActual; i++)
            new OperateToCommand((int) (Math.random() * 10), (int) (Math.random() * 10)).accept(visitor);

        Assert.assertEquals(visitor.getOperateToCounter(), operateToActual);
        Assert.assertEquals(visitor.getSetPositionCounter(), setPositionActual);
    }

    @Test
    public void testCurrentCommandTypeFilled() {
        DriverCommandManager manager = new DriverCommandManager();
        List<DriverCommand> commList = new ArrayList<DriverCommand>();
        CommandTypeCounterVisitor visitor = new CommandTypeCounterVisitor();
        int setPositionActual = 20;
        int operateToActual = 15;

        for (int i = 0; i < setPositionActual; i++)
            commList.add(new SetPositionCommand(i / 10, (i + 3) / 10));
        for (int i = 0; i < operateToActual; i++)
            commList.add(new OperateToCommand(i / 5, (i - 5) / 10));

        manager.setCurrentCommand(commList, "testListOfCommands");
        manager.getCurrentCommand().accept(visitor);

        Assert.assertEquals(visitor.getOperateToCounter(), operateToActual);
        Assert.assertEquals(visitor.getSetPositionCounter(), setPositionActual);

    }

    @Test
    public void testCurrentCommandTypeSingle() {
        DriverCommandManager manager = new DriverCommandManager();
        CommandTypeCounterVisitor visitor = new CommandTypeCounterVisitor();

        manager.setCurrentCommand(new OperateToCommand(3, 6));
        manager.getCurrentCommand().accept(visitor);

        Assert.assertEquals(visitor.getOperateToCounter(), 1);
        Assert.assertEquals(visitor.getSetPositionCounter(), 0);

    }
}
