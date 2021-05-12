package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.MacroRecorder;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MacroLoadListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        List<DriverCommand> commands = MacroRecorder.getCommands();
        if (!commands.isEmpty()) {
            CommandsFeature.getDriverCommandManager().setCurrentCommand(commands, "Recorded Macro");
        }
    }
}
