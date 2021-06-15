package edu.kis.powp.jobs2d.command.canvas;


import edu.kis.powp.jobs2d.command.canvas.Canvas;
import edu.kis.powp.jobs2d.drivers.transformation.Point;

public class RectangleCanvas implements Canvas {
    private final Point start;
    private final Point end;

    public RectangleCanvas(int height, int width){
        start = new Point(-(width/2), -(height/2));
        end = new Point(width/2, height/2);
    }

    @Override
    public boolean checkIfPointIsOnCanvas(Point point) {
        return (point.x > start.x && point.x < end.x) && (point.y > start.y && point.y < end.y);
    }
}
