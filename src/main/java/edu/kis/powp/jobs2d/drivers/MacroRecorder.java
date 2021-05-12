package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class MacroRecorder {
    private static Boolean status;
    private static final List<DriverCommand> commands = new ArrayList<>();

    public MacroRecorder() {
        status = false;
    }
    public static void stopRecording() {
        status = false;
    }
    public static void startRecording() {
        status = true;
        commands.clear();
    }
    public static List<DriverCommand> getCommands() {
        return new ArrayList<DriverCommand>(commands);
    }

    public static void addCommand(DriverCommand command){
        synchronized (commands) {
            commands.add(command);
        }
    }
}
