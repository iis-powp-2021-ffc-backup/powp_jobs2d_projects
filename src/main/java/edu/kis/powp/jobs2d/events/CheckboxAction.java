package edu.kis.powp.jobs2d.events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CheckboxAction extends AbstractAction {
    public CheckboxAction(String text) {
        super(text);
    }

    private List<ICheckBoxObserver> observers = new ArrayList<>();
    public void addObserver(ICheckBoxObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(ICheckBoxObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JCheckBox checkBox = (JCheckBox) event.getSource();
        if (checkBox.isSelected()) {
            for (ICheckBoxObserver observer : observers) {
                observer.onEnabled();
            }
        } else {
            for (ICheckBoxObserver observer : observers) {
                observer.onDisabled();
            }
        }
    }
}