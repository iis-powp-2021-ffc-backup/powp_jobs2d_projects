package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.controller.ICommandManagerController;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;


public class CommandManagerWindow extends JFrame implements WindowComponent {

    private final ICommandManagerController commandManagerController;
    private final JTextArea currentCommandField;
    private final JTextArea observerListField;
    private final JTextArea InputCommandsTextArea;
    private static final long serialVersionUID = 9204679248304669948L;

    public CommandManagerWindow(ICommandManagerController commandManagerController) {
        this.setTitle("Command Manager");
        this.setSize(400, 400);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        this.commandManagerController = commandManagerController;

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
        commandManagerController.clearCommand();
        updateCurrentCommandField();
    }

    public void updateCurrentCommandField() {
        currentCommandField.setText(commandManagerController.getCurrentCommandString());
    }

    public void deleteObservers() {
        commandManagerController.deleteObservers();
        updateObserverListField();
    }

    public void runCommand() {
        commandManagerController.runCommand();
    }

    public void resetObservers() {
        commandManagerController.resetObservers();
        updateObserverListField();
    }

    private void updateObserverListField() {
        String observerListString = "";
        List<Subscriber> commandChangeSubscribers = commandManagerController.getChangePublisher().getSubscribers();
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
            commandManagerController.loadCommands(path);
        } catch (IOException e) {
            currentCommandField.setText("Couldn't load commands");
        }
    }

    private void saveCommandsToJSON(String path) {
        try {
            commandManagerController.saveCommands(path);
        } catch (IOException e) {
            currentCommandField.setText("Could not save commands");
        }
    }
}
