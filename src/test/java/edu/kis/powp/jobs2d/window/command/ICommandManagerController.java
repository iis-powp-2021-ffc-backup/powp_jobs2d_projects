package edu.kis.powp.jobs2d.window.command;
import edu.kis.powp.jobs2d.command.ICompoundCommand;
import edu.kis.powp.observer.Subscriber;

import java.io.IOException;
import java.util.List;

public interface ICommandManagerController {
	void importCommand(String fileContent) throws IOException;
	String exportCommand() throws IOException;
	void clearCommand();
	void runCommand();
	void restoreCommand();
	String getCurrentCommandString();
	void deleteObservers();
	List<Subscriber> getObservers();
    void addToHistory();
    void setCurrentCommand(ICompoundCommand comm);
}
