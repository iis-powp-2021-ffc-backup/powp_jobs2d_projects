package edu.kis.powp.jobs2d.window.command;

import edu.kis.powp.observer.Subscriber;

public class HistoryCommandListChangeObserver implements Subscriber {

	private CommandManagerWindow commandManagerWindow;

	public HistoryCommandListChangeObserver(CommandManagerWindow commandManagerWindow) {
		super();
		this.commandManagerWindow = commandManagerWindow;
	}

	@Override
	public void update() {
		commandManagerWindow.getController().addToHistory();
	}

}
