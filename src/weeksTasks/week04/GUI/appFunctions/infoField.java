package weeksTasks.week04.GUI.appFunctions;

import weeksTasks.week04.GUI.AppDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class infoField implements ActionListener {
    public String info;

    public infoField(String info) {
        this.info = info;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Component source = (Component) e.getSource();
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(source), info);
    }
}
