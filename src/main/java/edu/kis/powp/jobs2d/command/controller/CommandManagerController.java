package edu.kis.powp.jobs2d.command.controller;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.parser.CommandDataModel;
import edu.kis.powp.jobs2d.command.manager.parser.CommandParser;
import edu.kis.powp.jobs2d.command.manager.parser.IDriverCommandParser;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommandManagerController implements ICommandManagerController {

    private final DriverCommandManager driverCommandManager;
    private List<Subscriber> observersBackup;
    private final IDriverCommandParser commandParser = new CommandParser();

    public CommandManagerController(DriverCommandManager driverCommandManager) {
        this.driverCommandManager = driverCommandManager;
    }

    @Override
    public void clearCommand() {
        driverCommandManager.clearCurrentCommand();
    }

    @Override
    public void deleteObservers() {
        this.observersBackup = new ArrayList<>(getChangePublisher().getSubscribers());
        getChangePublisher().clearObservers();
    }

    @Override
    public void runCommand() {
        DriverCommand command = driverCommandManager.getCurrentCommand();
        command.execute(DriverFeature.getDriverManager().getCurrentDriver());
    }

    @Override
    public void resetObservers() {
        if (observersBackup == null) {
            return;
        }

        for (Subscriber observer : this.observersBackup) {
            getChangePublisher().addSubscriber(observer);
        }
        this.observersBackup = null;
    }

    @Override
    public String getCurrentCommandString() {
        return driverCommandManager.getCurrentCommandString();
    }

    @Override
    public Publisher getChangePublisher() {
        return driverCommandManager.getChangePublisher();
    }


    @Override
    public void loadCommands(String path) throws IOException {
        String stringifiedCommand = new String(Files.readAllBytes(Paths.get(path)));
        CommandDataModel inputCommandDataModel = commandParser.parseFromString(stringifiedCommand);
        driverCommandManager.setCurrentCommand(
            inputCommandDataModel.getDriverCommand(),
            inputCommandDataModel.getDriverCommandName()
        );
    }

    @Override
    public void saveCommands(String path) throws IOException {
        DriverCommand driverCommand = driverCommandManager.getCurrentCommand();
        String parsedDriverCommand = commandParser.parseToString(driverCommand);
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(parsedDriverCommand);
        writer.close();
    }
}
