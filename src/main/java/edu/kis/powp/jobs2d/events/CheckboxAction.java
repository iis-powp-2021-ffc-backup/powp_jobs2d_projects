package edu.kis.powp.jobs2d.events;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CheckboxAction extends AbstractAction {
    public CheckboxAction(String text) {
        super(text);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JCheckBox checkBox = (JCheckBox) event.getSource();
        if (checkBox.isSelected()) {
            System.out.println("Enabled");
            // TODO: Raise OnMouseEnabled
        } else {
            System.out.println("Disabled");
            // TODO: Raise OnMouseDisabled
        }
    }
}