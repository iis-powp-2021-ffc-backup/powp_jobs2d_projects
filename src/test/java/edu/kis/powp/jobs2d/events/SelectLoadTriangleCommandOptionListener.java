package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.figurefactory.TriangleFigureComplexCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SelectLoadTriangleCommandOptionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ICompoundCommand command = new TriangleFigureComplexCommand().createCompoundCommand();

        DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
        manager.setCurrentCommand(command);
    }
}
