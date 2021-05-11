package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.observer.Subscriber;

public class UsageSubscriber implements Subscriber {
    private CommandManagerWindow commandManagerWindow;
    private int setOperations = 0;
    private int allOperations = 0;
    private double distance = 0;
    public UsageSubscriber (CommandManagerWindow commandManagerWindow) {
        super();
        this.commandManagerWindow = commandManagerWindow;
    }

    public String toString(){
        return "Number of setOperations = " + setOperations + "\ntotal number of operations = " + allOperations
                + "\ntotal distance = " + distance;
    }

    public void setAllOperations(int allOperations) {
        this.allOperations = allOperations;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setSetOperations(int setOperations) {
        this.setOperations = setOperations;
    }

    @Override
    public void update() {
        commandManagerWindow.updateCurrentCommandField();
    }
}
