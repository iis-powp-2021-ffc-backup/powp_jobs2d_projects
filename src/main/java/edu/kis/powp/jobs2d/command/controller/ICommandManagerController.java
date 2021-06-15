package edu.kis.powp.jobs2d.command.controller;
import edu.kis.powp.observer.Publisher;

import java.io.IOException;


public interface ICommandManagerController {
    void clearCommand();
    void deleteObservers();
    void runCommand();
    void resetObservers();
    String getCurrentCommandString();
    Publisher getChangePublisher();
    void loadCommands(String path) throws IOException;
    void saveCommands(String path) throws IOException;
}
