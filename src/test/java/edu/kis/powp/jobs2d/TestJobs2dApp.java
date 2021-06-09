package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.json.JsonCommandImporter;
import edu.kis.powp.jobs2d.drivers.CompositeDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.MouseClickAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.UsageMonitoringDriver;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.jobs2d.window.command.CommandManagerController;
import edu.kis.powp.jobs2d.window.command.CommandManagerWindow;
import edu.kis.powp.jobs2d.window.command.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.window.command.ICommandManagerController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 *
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);

		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(driverFeature.getDriverManager());
		SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(driverFeature.getDriverManager());

		RectangleListener rectangleListener = new RectangleListener(driverFeature.getDriverManager());
		TriangleListener triangleListener = new TriangleListener(driverFeature.getDriverManager());


		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);

		application.addTest("Rectangle", rectangleListener);
		application.addTest("Triangle", triangleListener);

	}

	/**
	 * Setup test using driver commands in context.
	 *
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);

		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());

		application.addTest("▶ Run command", new SelectRunCurrentCommandOptionListener(driverFeature.getDriverManager()));

		application.addTest("Start recording", new MacroStartListener(driverFeature.getDriverManager()));

		application.addTest("Stop recording", new MacroStopListener(driverFeature.getDriverManager()));

		application.addTest("Load recorded", new MacroLoadListener());
	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 *
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);
		DrawerFeature drawerFeature = FeatureManager.getFeature(DrawerFeature.class);

		Job2dDriver loggerDriver = new LoggerDriver();
		DrawPanelController drawerController = drawerFeature.getDrawerController();
		LineDriverAdapter driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		driverFeature.addDriver("Line Simulator", driver);
		driverFeature.getDriverManager().setCurrentDriver(driver);

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		driverFeature.addDriver("Special line Simulator", driver);
		CompositeDriver compositeDriver = new CompositeDriver();
		UsageMonitoringDriver usageMonitoringDriver = new UsageMonitoringDriver(driver);
		java.util.List<Job2dDriver> allDrivers = driverFeature.getDriverManager().getAllDrivers();
		application.addComponentMenu(Job2dDriver.class, "Drivers utils");
		application.addComponentMenuElementWithCheckBox(Job2dDriver.class, "LoggerDriver", (ActionEvent e) ->
				{
					if (allDrivers.contains(loggerDriver))
						allDrivers.remove(loggerDriver);
					else
						allDrivers.add(loggerDriver);

				}
				, false);
		application.addComponentMenuElementWithCheckBox(Job2dDriver.class, "UsageMonitor", (ActionEvent e) ->
				{
					if (allDrivers.contains(usageMonitoringDriver))
						allDrivers.remove(usageMonitoringDriver);
					else{
						allDrivers.add(usageMonitoringDriver);
					}
				}
				, false);

		MouseClickAdapter mouseClickAdapter = new MouseClickAdapter(application.getFreePanel(), driverFeature.getDriverManager());
		mouseClickAdapter.enable();
	}

	private static void setupMonitoringDeviceTests(Application application) {

		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);
		DrawerFeature drawerFeature = FeatureManager.getFeature(DrawerFeature.class);
		application.addTest("Monitoring device Test", new SelectMonitoringDeviceTestFigureOptionListener(driverFeature.getDriverManager()));
		DrawPanelController drawerController = drawerFeature.getDrawerController();

	}

	private static void setupWindows(Application application) {
		CommandsFeature commandsFeature = FeatureManager.getFeature(CommandsFeature.class);
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);

		ICommandManagerController commandManagerController = new CommandManagerController(driverFeature.getDriverManager(),
				commandsFeature.getDriverCommandManager(), new JsonCommandImporter());
		CommandManagerWindow commandManagerWindow = new CommandManagerWindow(commandManagerController);
		application.addWindowComponent("Command Manager", commandManagerWindow);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManagerWindow);
		commandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 *
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				FeatureManager.addFeatures(new DrawerFeature(), new CommandsFeature(), new DriverFeature());
				FeatureManager.setup(app);
				setupMonitoringDeviceTests(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);

				CommandsFeature commandsFeature = FeatureManager.getFeature(CommandsFeature.class);
				commandsFeature.getDriverCommandManager().saveSubscribers();

				app.setVisibility(true);
			}
		});
	}

}