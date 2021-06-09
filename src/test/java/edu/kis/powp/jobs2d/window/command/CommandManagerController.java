package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.jobs2d.command.CommandImporter;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.jobs2d.command.history.HistoryCommandList;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.observer.Subscriber;

import java.io.IOException;
import java.util.List;

public class CommandManagerController implements ICommandManagerController {

	private DriverManager driverManager;
	private DriverCommandManager commandManager;
	private CommandImporter importer;

	public CommandManagerController(DriverManager driverManager, DriverCommandManager commandManager, CommandImporter importer) {
		this.driverManager = driverManager;
		this.commandManager = commandManager;
		this.importer = importer;
	}

	@Override
	public void importCommand(String fileContent) throws IOException {
		commandManager.setCurrentCommand(importer.importCommand(fileContent));
	}

	@Override
	public String exportCommand() throws IOException {
		return importer.exportCommand((CompoundCommand) commandManager.getCurrentCommand());
	}

	@Override
	public void clearCommand() {
		commandManager.clearCurrentCommand();
	}

	@Override
	public void restoreCommand() {
		commandManager.restoreSubscribers();
	}

	@Override
	public String getCurrentCommandString() {
		return commandManager.getCurrentCommandString();
	}

	@Override
	public void deleteObservers() {
		commandManager.getChangePublisher().clearObservers();
	}

	@Override
	public List<Subscriber> getObservers() {
		return commandManager.getChangePublisher().getSubscribers();
	}

	@Override
	public ICompoundCommand getCurrentCommand() {
		return (ICompoundCommand) commandManager.getCurrentCommand();
	}

	@Override
	public void runCommand() {
		new SelectRunCurrentCommandOptionListener(driverManager).actionPerformed(null);
	}

	@Override
	public void addToHistory() {
		HistoryCommandList.addCommandToList(commandManager.getCurrentCommandString(), (ICompoundCommand)  commandManager.getCurrentCommand());
	}

}
