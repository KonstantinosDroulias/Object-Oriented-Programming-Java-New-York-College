package weeksTasks.week04.GUI.appFunctions;

import weeksTasks.week04.CSVManager;
import weeksTasks.week04.CustomerOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class exportCSV implements ActionListener {
    private List<CustomerOrder> orders;

    public exportCSV(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save CSV File");
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Optional: add ".csv" extension if missing
            if (!file.getName().toLowerCase().endsWith(".csv")) {
                file = new File(file.getAbsolutePath() + ".csv");
            }

            // ✅ No try-catch needed if CSVManager handles the exception
            CSVManager.exportToCSV(file.getAbsolutePath(), orders);
            JOptionPane.showMessageDialog(null, "Exported to:\n" + file.getAbsolutePath());
        }
    }
}
