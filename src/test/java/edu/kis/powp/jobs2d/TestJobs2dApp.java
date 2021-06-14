package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.json.JsonCommandImporter;
import edu.kis.powp.jobs2d.window.command.*;
import edu.kis.powp.jobs2d.drivers.CompositeDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.UsageMonitoringDriver;
import edu.kis.powp.jobs2d.drivers.adapter.MouseClickAdapter;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.FeatureManager;
import edu.kis.powp.jobs2d.transformations.*;
import edu.kis.powp.jobs2d.transformations.TransformationDriver;

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

		application.addTest("Test if exceeds A4", new TestIfCommandFits(PaperFormats.A4_v));
		application.addTest("Test if exceeds B3", new TestIfCommandFits(PaperFormats.B3_v));
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

	private static void setupVisitorTests(Application application) {
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);
		application.addTest("Rotate", new SelectRotateFigureListener(driverFeature.getDriverManager()));
		application.addTest("Scale", new SelectScaleFigureListener(driverFeature.getDriverManager()));
		application.addTest("Mirror", new SelectMirrorFigureListener(driverFeature.getDriverManager()));
		application.addTest("Move", new SelectMoveFigureListener(driverFeature.getDriverManager()));
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
		driverFeature.addDriver("Logger driver", loggerDriver);

		DrawPanelController drawerController = drawerFeature.getDrawerController();
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		driverFeature.addDriver("Line Simulator", driver);
		driverFeature.getDriverManager().setCurrentDriver(driver);

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		driverFeature.addDriver("Special line Simulator", driver);

		TransformationDriver scaleTransformationDriver = new TransformationDriver(
				new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line")
		);
		scaleTransformationDriver.addNewTransformation(new Scale(0.5d, 0.5d));
		driverFeature.addDriver("Scale 0.5x", scaleTransformationDriver);

		TransformationDriver scaleTransformationDriver2 = new TransformationDriver(
				new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line")
		);
		scaleTransformationDriver2.addNewTransformation(new Scale(2d, 2d));
		driverFeature.addDriver("Scale 2x", scaleTransformationDriver2);

		TransformationDriver rotateTransformationDriver = new TransformationDriver(
				new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line")
		);
		rotateTransformationDriver.addNewTransformation(new Rotate(45));
		driverFeature.addDriver("Rotate 45°", rotateTransformationDriver);

		TransformationDriver flipHorizontalTransformationDriver = new TransformationDriver(
				new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line")
		);
		flipHorizontalTransformationDriver.addNewTransformation(new HorizontalFlip());
		driverFeature.addDriver("Flip Horizontal", flipHorizontalTransformationDriver);


		TransformationDriver flipVerticalTransformationDriver = new TransformationDriver(
				new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line")
		);
		flipVerticalTransformationDriver.addNewTransformation(new VerticalFlip());
		driverFeature.addDriver("Flip Vertical", flipVerticalTransformationDriver);

		CompositeDriver compositeDriver = new CompositeDriver();
		compositeDriver.add(loggerDriver);
		compositeDriver.add(driver);
		driverFeature.addDriver("Composite Driver", compositeDriver);

		MouseClickAdapter mouseClickAdapter = new MouseClickAdapter(application.getFreePanel(), driverFeature.getDriverManager());

		mouseClickAdapter.enable();
	}
	private static void setupMonitoringDeviceTests(Application application) {
		DriverFeature driverFeature = FeatureManager.getFeature(DriverFeature.class);
		DrawerFeature drawerFeature = FeatureManager.getFeature(DrawerFeature.class);

		application.addTest("Monitoring device Test", new SelectMonitoringDeviceTestFigureOptionListener(driverFeature.getDriverManager()));
		DrawPanelController drawerController = drawerFeature.getDrawerController();

		LineDriverAdapter lineDriverAdapter = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		UsageMonitoringDriver usageMonitoringDriver = new UsageMonitoringDriver(lineDriverAdapter);

		driverFeature.addDriver("Usage monitoring Simulator", usageMonitoringDriver);

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
		HistoryCommandListChangeObserver historyObserver = new HistoryCommandListChangeObserver(
				commandManagerController);
		commandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(historyObserver);
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
				setupCommandTests(app);
				setupPresetTests(app);
				setupLogger(app);
				setupWindows(app);
				setupVisitorTests(app);

				CommandsFeature commandsFeature = FeatureManager.getFeature(CommandsFeature.class);
				commandsFeature.getDriverCommandManager().saveSubscribers();

				app.setVisibility(true);
			}
		});
	}

}
