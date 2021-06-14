package edu.kis.powp.jobs2d.features;

import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {
    private List<Feature> features = new ArrayList<>();
    
    public void add(Feature f) {
        features.add(f);
    }
    
    public void addMany(Feature ... f) {
        for(Feature i : f) {
            features.add(i);
        }
    }
    
    public void executeAll(){
        for(Feature i : features){
            i.setup();
        }
    }
}
