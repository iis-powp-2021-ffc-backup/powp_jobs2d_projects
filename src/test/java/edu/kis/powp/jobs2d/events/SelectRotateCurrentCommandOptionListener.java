package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.TransformationsVisitor;
import edu.kis.powp.jobs2d.drivers.transformation.Rotate;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectRotateCurrentCommandOptionListener implements ActionListener {
    private final DriverCommandManager driverCommandManager;

    public SelectRotateCurrentCommandOptionListener(DriverCommandManager driverCommandManager) {
        this.driverCommandManager = driverCommandManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommand command = driverCommandManager.getCurrentCommand();
        TransformationsVisitor transformationsVisitor = new TransformationsVisitor(new Rotate(Math.PI / 2));
        command.accept(transformationsVisitor);
        command = transformationsVisitor.getTransformedComplexCommand();
        CommandsFeature.getDriverCommandManager().setCurrentCommand(command);
    }
}
