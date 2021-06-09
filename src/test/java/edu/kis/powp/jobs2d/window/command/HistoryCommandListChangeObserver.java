package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.observer.Subscriber;

public class HistoryCommandListChangeObserver implements Subscriber {

	private ICommandManagerController commandManagerController;

	public HistoryCommandListChangeObserver(ICommandManagerController commandManagerController) {
		super();
		this.commandManagerController = commandManagerController;
	}

	@Override
	public void update() {
		commandManagerController.addToHistory();
	}

}
