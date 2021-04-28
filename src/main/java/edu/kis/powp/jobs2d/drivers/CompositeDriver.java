package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.List;

public class CompositeDriver implements Job2dDriver {

    private List<Job2dDriver> drivers;
    private int setOperations = 0;
    private int allOperations = 0;
    private int startX = 0;
    private int startY = 0;
    private float distance = 0;
    public CompositeDriver(){
        drivers = new ArrayList<>();
    }

    @Override
    public void setPosition(int x, int y) {
        drivers.forEach(e->e.setPosition(x, y));
        this.allOperations ++;
    }

    @Override
    public void operateTo(int x, int y) {
        drivers.forEach(e->e.operateTo(x, y));
        this.allOperations ++;
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

    public float getDistance() {
        return distance;
    }

    public int getAllOperations() {
        return allOperations;
    }

    public int getSetOperations() {
        return setOperations;
    }

    @Override
    public String toString(){
        return "Composite driver";
    }
}
