package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.Job2dDriver;

import static java.lang.Math.pow;

public class UsageDriver implements Job2dDriver {
    private int setOperations = 0;
    private int allOperations = 0;
    private int startX = 0;
    private int startY = 0;
    private double distance = 0;
    private UsageSubscriber usageSubscriber;
    public UsageDriver(UsageSubscriber usageSubscriber){
        this.usageSubscriber = usageSubscriber;
    }
    @Override
    public void setPosition(int i, int i1) {
        this.setOperations += 1;
        this.allOperations += 1;
        startX = i;
        startY = i1;
        this.usageSubscriber.setSetOperations(this.setOperations);
        this.usageSubscriber.setAllOperations(this.allOperations);
        this.usageSubscriber.setDistance(this.distance);
    }

    @Override
    public void operateTo(int i, int i1) {
        this.distance += calculateDistance(i, startX, i1, startY);
        this.allOperations += 1;
        startX = i;
        startY = i1;
        this.usageSubscriber.setSetOperations(this.setOperations);
        this.usageSubscriber.setAllOperations(this.allOperations);
        this.usageSubscriber.setDistance(this.distance);
    }


    private double calculateDistance(int x1, int x2, int y1, int y2){
        return Math.sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }

}

