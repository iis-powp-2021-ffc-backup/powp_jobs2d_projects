package edu.kis.powp.jobs2d.command.visitor;

import edu.kis.powp.jobs2d.command.canvas.Canvas;
import edu.kis.powp.jobs2d.command.canvas.RectangleCanvas;

public class CanvasFactory {
    public static Canvas getCanvasA4(){
        return new RectangleCanvas(210,297);
    }

    public static Canvas getCanvasA5(){
        return new RectangleCanvas(148, 210);
    }
}
