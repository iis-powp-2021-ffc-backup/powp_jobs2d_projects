package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.canvas.Canvas;
import edu.kis.powp.jobs2d.command.visitor.CommandCanvasVisitor;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SelectCommandCanvasVisitorListener implements ActionListener {

    private Logger logger = Logger.getLogger("global");
    private DriverManager driverManager ;
    private Canvas canvas;

    public SelectCommandCanvasVisitorListener(DriverManager driverManager, Canvas canvas) {
        this.driverManager = driverManager;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("Testing Visitor for CommandCanvasVisitor");

        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if(command==null){
            logger.info("No command loaded");
        }else {
            CommandCanvasVisitor visitor = new CommandCanvasVisitor(canvas);

            command.accept(visitor);

            if(visitor.checkIfAllPointsOnCanvas())
                logger.info("Not all points are on canvas");
            else
                logger.info("All points are on canvas");
        }
    }
}
