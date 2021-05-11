package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class CompositeDriver implements Job2dDriver {

    private List<Job2dDriver> drivers;
    private int setOperations = 0;
    private int allOperations = 0;
    private int startX = 0;
    private int startY = 0;
    private double distance = 0;
    private UsageSubscriber usageSubscriber;
    public CompositeDriver(UsageSubscriber usageSubscriber){
        drivers = new ArrayList<>();
        this.usageSubscriber = usageSubscriber;
    }

    @Override
    public void setPosition(int x, int y) {
        drivers.forEach(e->e.setPosition(x, y));
        this.distance += calculateDistance(startX, x, startY, y);
        this.setOperations += drivers.size();
        this.usageSubscriber.setSetOperations(this.setOperations);

    }

    @Override
    public void operateTo(int x, int y) {
        this.distance += calculateDistance(x, startX, y, startY);
        drivers.forEach(e->e.operateTo(x, y));
        this.allOperations += drivers.size();
        this.usageSubscriber.setAllOperations(this.allOperations);
        this.usageSubscriber.setDistance(distance);

    }

    public void add(Job2dDriver driver){
        drivers.add(driver);
    }

    public Job2dDriver[] getChildren(){
        return drivers.toArray(new Job2dDriver[drivers.size()]);
    }

    public void remove(Job2dDriver children){
        drivers.remove(children);
    }

    private double calculateDistance(int x1, int x2, int y1, int y2){
        return Math.ceil(Math.sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2)));
    }
    @Override
    public String toString(){
        return "Composite driver";
    }
}
