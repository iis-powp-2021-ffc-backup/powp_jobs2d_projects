package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.command.canvas.Canvas;
import edu.kis.powp.jobs2d.command.visitor.CanvasFactory;
import edu.kis.powp.jobs2d.drivers.DriverInfoUpdater;
import edu.kis.powp.jobs2d.drivers.TransformationDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.drivers.transformation.Rotate;
import edu.kis.powp.jobs2d.drivers.transformation.Scale;
import edu.kis.powp.jobs2d.events.*;
import edu.kis.powp.jobs2d.events.SelectLoadSecretCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigure2OptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.features.ApplicationManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.MacroFeature;
import edu.kis.powp.jobs2d.observer.ICheckBoxObserver;
import edu.kis.powp.jobs2d.features.*;
import edu.kis.powp.jobs2d.observer.MouseControlLoggerObserver;
import edu.kis.powp.jobs2d.observer.MouseControlObserver;

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
        SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
                DriverFeature.getDriverManager());
        SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
                DriverFeature.getDriverManager());

        application.addTest("Figure Joe 1", selectTestFigureOptionListener);
        application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
    }

    /**
     * Setup test using driver commands in context.
     *
     * @param application Application context.
     */
    private static void setupCommandTests(Application application) {
        Canvas paperA4 = CanvasFactory.getCanvasA4();
        Canvas paperA5 = CanvasFactory.getCanvasA5();
        application.addTest("Canvas checker A4", new SelectCommandCanvasVisitorListener(DriverFeature.getDriverManager(), paperA4));
        application.addTest("Canvas checker A5", new SelectCommandCanvasVisitorListener(DriverFeature.getDriverManager(), paperA5));
    }

    /**
     * Setup driver manager, and set default Job2dDriver for application.
     *
     * @param application Application context.
     */
    private static void setupDrivers(Application application) {
        Job2dDriver loggerDriver = new LoggerDriver();
        DriverFeature.addDriver("Logger driver", loggerDriver);

        DrawPanelController drawerController = DrawerFeature.getDrawerController();
        Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
        DriverFeature.addDriver("Line Simulator", driver);
        DriverFeature.getDriverManager().setCurrentDriver(driver);

        driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
        DriverFeature.addDriver("Special line Simulator", driver);

        TransformationDriver scaleDriver = new TransformationDriver(new Scale(0.5d, 0.5d), new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line"));
        DriverFeature.addDriver("Scale (0.5x)", scaleDriver);
        TransformationDriver scaleDriver2 = new TransformationDriver(new Scale(1.5d, 1.5d), new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line"));
        DriverFeature.addDriver("Scale (1.5x)", scaleDriver2);


        TransformationDriver rotateDriver = new TransformationDriver(new Rotate(Math.PI / 2), new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line"));
        DriverFeature.addDriver("Rotate (PI/2)", rotateDriver);
        TransformationDriver rotateDriver2 = new TransformationDriver(new Rotate(Math.PI / 3), new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line"));
        DriverFeature.addDriver("Rotate (PI/3)", rotateDriver2);

        TransformationDriver rotateDriver3 = new TransformationDriver(new Scale(1d, -1d), new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line"));
        DriverFeature.addDriver("Flip (vertically)", rotateDriver3);
        TransformationDriver rotateDriver4 = new TransformationDriver(new Scale(-1d, 1d), new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic line"));
        DriverFeature.addDriver("Flip (horizontally)", rotateDriver4);

        DriverInfoUpdater subscriber = new DriverInfoUpdater();
        DriverFeature.getDriverManager().getPublisher().addSubscriber(subscriber);
    }

    private static void setupExtensions(Application application) {
    	ExtensionFeature.addDriver("Macro", MacroFeature.getDriver());
    	Job2dDriver loggerDriver = new LoggerDriver();
        ExtensionFeature.addDriver("Logger", loggerDriver);
    }
    
    private static void setupWindows(Application application) {
        CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
        application.addWindowComponent("Command Manager", commandManager);

        CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
                commandManager);
        CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);
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

    private static void setupFeaturesMenu(Application application) {
        SelectTestMouseListener selectTestMouseListener = new SelectTestMouseListener(
                DriverFeature.getDriverManager(), application.getFreePanel());

        application.addComponentMenu(Feature.class, "Features");
        application.addComponentMenuElement(Feature.class, "Load secret command", new SelectLoadSecretCommandOptionListener());
        application.addComponentMenuElement(Feature.class, "Load triangle command", new SelectLoadTriangleCommandOptionListener());
        application.addComponentMenuElement(Feature.class, "Rotate command", new SelectRotateCurrentCommandOptionListener(CommandsFeature.getDriverCommandManager()));
        application.addComponentMenuElement(Feature.class, "Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));
        application.addComponentMenuElement(Feature.class, "Count command", new SelectCurrentCommandCounter(CommandsFeature.getDriverCommandManager()));
        application.addComponentMenuElement(Feature.class, "Load Macro", new SelectLoadMacroOptionListener(MacroFeature.getDriver(), CommandsFeature.getDriverCommandManager()));
        application.addComponentMenuElement(Feature.class, "Clear Macro", new SelectClearMacroOptionListener(MacroFeature.getDriver(), CommandsFeature.getDriverCommandManager()));
        application.addComponentMenuElement(Feature.class, "Print usage report", (ActionEvent e) -> MonitorFeature.printReport());
    }

    private static void setupMouseControl(Application application) {
        ICheckBoxObserver mouseControlObserver =  new MouseControlObserver(DriverFeature.getDriverManager(), application.getFreePanel());
        MouseFeature.getEnableMouseAction().addObserver(mouseControlObserver);
        MouseFeature.getEnableMouseAction().addObserver(new MouseControlLoggerObserver());
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Application app = new Application("Jobs 2D");
                ApplicationManager manager = new ApplicationManager();
                manager.addMany(new DriverFeature(app), new CommandsFeature(), new DrawerFeature(app), new MacroFeature(), new MouseFeature(app), new MonitorFeature());
                manager.add(new ExtensionFeature(app, DriverFeature.getDriverManager()));
                manager.executeAll();

                setupDrivers(app);
                setupPresetTests(app);
                setupCommandTests(app);
                setupLogger(app);
                setupWindows(app);
                setupFeaturesMenu(app);
                setupMouseControl(app);
                setupExtensions(app);

                app.setVisibility(true);
            }
        });
    }

}
