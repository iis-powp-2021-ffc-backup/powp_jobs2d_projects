package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.FileOpertor;
import edu.kis.powp.jobs2d.command.history.HistoryCommandList;
import edu.kis.powp.jobs2d.command.history.HistoryCommandObject;
import edu.kis.powp.jobs2d.events.SelectHistoryCommandOptionListener;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class CommandManagerWindow extends JFrame implements WindowComponent {
	/**
	 *
	 */
	private static final long serialVersionUID = 9204679248304669948L;
	private JTextArea currentCommandField;
	private String observerListString;
	private JTextArea observerListField;
	private String selectedFilePath;

	private ICommandManagerController controller;

	private JTextArea listTitle;
	private JList<HistoryCommandObject> commandList;

	public CommandManagerWindow(ICommandManagerController controller) {
		this.controller = controller;
		this.commandList = new JList<>(HistoryCommandList.getHistoryCommandList());
		this.setTitle("Command Manager");
		this.setSize(400, 600);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.weighty = 1;
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		content.add(currentCommandField, c);
		updateCurrentCommandField();

		JButton btnRunCommand = new JButton("Run command");
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		btnRunCommand.addActionListener((ActionEvent e) -> runCommand());
		content.add(btnRunCommand, c);

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		c.gridx = 1;
		c.gridy = 2;
		content.add(btnClearCommand, c);

		JButton btnImportCommand = new JButton("Import command");
		c.gridx = 0;
		c.gridy = 3;
		btnImportCommand.addActionListener((ActionEvent e) -> this.importCommand());
		content.add(btnImportCommand, c);

		JButton btnExportCommand = new JButton("Export command");
		c.gridx = 1;
		c.gridy = 3;
		btnExportCommand.addActionListener((ActionEvent e) -> this.exportCommand());
		content.add(btnExportCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
		c.gridx = 0;
		c.gridy = 4;
		content.add(btnClearObservers, c);

		JButton btnResetObservers = new JButton("Restore observers");
		btnResetObservers.addActionListener((ActionEvent e) -> restoreObservers());
		c.gridx = 1;
		c.gridy = 4;
		content.add(btnResetObservers, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.weighty = 1;

		listTitle = new JTextArea("List of previously used commands:");
		listTitle.setEditable(false);
		content.add(listTitle, c);

		c.gridy = 6;

		JScrollPane scroll = new JScrollPane(commandList);
		scroll.setMinimumSize (new Dimension (100,200));
		content.add(scroll, c);
		commandList.addListSelectionListener(new SelectHistoryCommandOptionListener(controller));
	}


	private void clearCommand() {
		controller.clearCommand();
		updateCurrentCommandField();
	}

	private void runCommand() {
		controller.runCommand();
	}

	private void importCommand() {
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Choose Json file");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Json files", "json");
		fileChooser.addChoosableFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFilePath = fileChooser.getSelectedFile().getAbsoluteFile().toString();
			try {
				String fileContent = FileOpertor.loadFileContent(selectedFilePath);
				controller.importCommand(fileContent);
				updateCurrentCommandField();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(selectedFilePath);
		}
	}

	private void exportCommand() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Json files", "json");
		fileChooser.addChoosableFileFilter(filter);

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			selectedFilePath = fileChooser.getSelectedFile().getAbsoluteFile().toString();
			try {
				String data = controller.exportCommand();
				FileOpertor.writeFileContent(selectedFilePath, data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(selectedFilePath);
		}
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(controller.getCurrentCommandString());
	}

	public void deleteObservers() {
		controller.deleteObservers();
		updateObserverListField();
	}

	private void restoreObservers() {
		controller.restoreCommand();
		updateObserverListField();
	}

	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = controller.getObservers();
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
}
