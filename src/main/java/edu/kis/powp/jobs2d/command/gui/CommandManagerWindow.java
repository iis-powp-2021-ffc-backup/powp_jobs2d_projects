package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.command.manager.parser.CommandDataModel;
import edu.kis.powp.jobs2d.command.manager.parser.CommandParser;
import edu.kis.powp.jobs2d.command.manager.parser.IDriverCommandParser;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class CommandManagerWindow extends JFrame implements WindowComponent {

    private final DriverCommandManager commandManager;

    private final JTextArea currentCommandField;

    private String observerListString;
    private final JTextArea observerListField;
    private final JTextArea InputCommandsTextArea;

    private final IDriverCommandParser commandParser = new CommandParser();

    /**
     *
     */
    private static final long serialVersionUID = 9204679248304669948L;
    private final DriverCommandManager commandManager;
    private final JTextArea currentCommandField;
    private String observerListString;
    private final JTextArea observerListField;
    private List<Subscriber> observersBackup = null;

    public CommandManagerWindow(DriverCommandManager commandManager) {
        this.setTitle("Command Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManager = commandManager;

        GridBagConstraints c = new GridBagConstraints();

        observerListField = new JTextArea("");
        observerListField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(observerListField, c);
        updateObserverListField();

        currentCommandField = new JTextArea("");
        currentCommandField.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(currentCommandField, c);
        updateCurrentCommandField();

        InputCommandsTextArea = new JTextArea("");
        InputCommandsTextArea.setEditable(true);
        InputCommandsTextArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        InputCommandsTextArea.setLineWrap(true);

        JScrollPane InputCommandsField = new JScrollPane(InputCommandsTextArea);
        content.add(InputCommandsField, c);

        JButton jsonLoadCommands = new JButton("Load commands");
        jsonLoadCommands.addActionListener((ActionEvent e) -> this.loadCommandsFromJSON(InputCommandsTextArea.getText().trim()));
        content.add(jsonLoadCommands, c);

        JButton jsonSaveCommands = new JButton("Save commands");
        jsonSaveCommands.addActionListener((ActionEvent e) -> this.saveCommandsToJSON(InputCommandsTextArea.getText().trim()));
        content.add(jsonSaveCommands, c);


        JButton btnClearCommand = new JButton("Clear command");
        btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);

        JButton btnRunCommand = new JButton("Run command");
        btnRunCommand.addActionListener((ActionEvent e) -> this.runCommand());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnRunCommand, c);

        JButton btnResetCommand = new JButton("Reset observers");
        btnResetCommand.addActionListener((ActionEvent e) -> this.resetObservers());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnResetCommand, c);

        JButton btnClearObservers = new JButton("Delete observers");
        btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearObservers, c);
    }

    private void clearCommand() {
        commandManager.clearCurrentCommand();
        updateCurrentCommandField();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManager.getCurrentCommandString());
    }

    public void deleteObservers() {
        this.observersBackup = new ArrayList<>(commandManager.getChangePublisher().getSubscribers());
        commandManager.getChangePublisher().clearObservers();
        this.updateObserverListField();
    }

    public void runCommand() {
        DriverCommand command = commandManager.getCurrentCommand();
        command.execute(DriverFeature.getDriverManager().getCurrentDriver());
    }

    public void resetObservers() {
        if (observersBackup == null)
            return;
        for (Subscriber observer : this.observersBackup)
            commandManager.getChangePublisher().addSubscriber(observer);
        this.updateObserverListField();
        this.observersBackup = null;
    }

    private void updateObserverListField() {
        observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
        for (Subscriber observer : commandChangeSubscribers) {
            observerListString += observer.toString() + System.lineSeparator();
        }
        if (commandChangeSubscribers.isEmpty())
            observerListString = "No observers loaded";

        observerListField.setText(observerListString);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        updateObserverListField();
        this.setVisible(!this.isVisible());
    }

    private void loadCommandsFromJSON(String path) {
        try {
            String jsonInput = new String(Files.readAllBytes(Paths.get(path)));
            CommandDataModel inputCommandDataModel = commandParser.parseFromString(jsonInput);
            commandManager.setCurrentCommand(
                    inputCommandDataModel.getDriverCommand(),
                    inputCommandDataModel.getDriverCommandName()
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.setVisible(!this.isVisible());
    }

    private void saveCommandsToJSON(String path) {
        try {

            DriverCommand driverCommand = commandManager.getCurrentCommand();
            String json = commandParser.parseToString(driverCommand);
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(json);

            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
