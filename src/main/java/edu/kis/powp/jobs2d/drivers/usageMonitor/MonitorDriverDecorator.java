package edu.kis.powp.jobs2d.drivers.usageMonitor;

import edu.kis.powp.jobs2d.Job2dDriver;

public class MonitorDriverDecorator implements IMonitorDriverDecorator
{
    private Job2dDriver job2dDriver;
    private double headDistance = 0;
    private double operationDistance = 0;
    private int currentX = 0;
    private int currentY = 0;

    public MonitorDriverDecorator(Job2dDriver job2dDriver)
    {
        this.job2dDriver = job2dDriver;
    }

    @Override
    public void setPosition(int x, int y)
    {
        job2dDriver.setPosition(x, y);
        headDistance += calculateDistance(currentX, currentY, x, y);
        currentX = x;
        currentY = y;
    }

    @Override
    public void operateTo(int x, int y)
    {
        job2dDriver.operateTo(x, y);
        headDistance += calculateDistance(currentX, currentY, x, y);
        operationDistance += calculateDistance(currentX, currentY, x, y);
        currentX = x;
        currentY = y;
    }

    @Override
    public double getHeadDistance()
    {
        return headDistance;
    }

    @Override
    public double getOperationDistance()
    {
        return operationDistance;
    }

    private double calculateDistance(int startX, int startY, int endX, int endY)
    {
        return Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
    }
}
