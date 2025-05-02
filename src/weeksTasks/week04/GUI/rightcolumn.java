package weeksTasks.week04.GUI;

import weeksTasks.week04.CustomerOrder;
import weeksTasks.week04.CustomerOrderList;
import weeksTasks.week04.GUI.appFunctions.exportCSV;
import weeksTasks.week04.GUI.guiComponents.CustomerOrderRenderer;
import weeksTasks.week04.GUI.guiComponents.LabelTextInput;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class rightcolumn extends JPanel {
    public static double totalIncome = 0;

    public rightcolumn() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(20, 10, 20, 30));

        JPanel searchPanel = new JPanel(new BorderLayout());
        LabelTextInput findTotalByEmail = new LabelTextInput("Search total by Email:", "Input Customer Email to find total spend example@example.com");
        LabelTextInput findTotalByDate = new LabelTextInput("Search total by Date:", "Input Date to find total earnings format yyyy-MM-DD");
        searchPanel.add(findTotalByEmail, BorderLayout.EAST);
        searchPanel.add(findTotalByDate, BorderLayout.WEST);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        CustomerOrderList orderList = new CustomerOrderList();
        orderList.loadFromDatabase();

        DefaultListModel<CustomerOrder> model = new DefaultListModel<>();
        for (CustomerOrder co : orderList) {
            model.addElement(co);  // this was CHATGPT code in hopes I will learn it and be able to do it on my own
        }

        JPanel listPanel = new JPanel(new BorderLayout());
        JList<CustomerOrder> jList = new JList<>(model);
        jList.setCellRenderer(new CustomerOrderRenderer());
        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        listPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel totalCSV = new JPanel(new BorderLayout());
        JLabel lblTotalEarn = new JLabel("Total: " + totalIncome);
        JButton btnExportCSV = new JButton("Export CSV");
        btnExportCSV.addActionListener(new exportCSV(orderList));
        totalCSV.add(lblTotalEarn, BorderLayout.WEST);
        totalCSV.add(btnExportCSV, BorderLayout.EAST);

        JPanel totalRowContainer = new JPanel(new BorderLayout());
        totalRowContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        totalRowContainer.add(totalCSV, BorderLayout.CENTER);


        this.add(searchPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(listPanel);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalStrut(10));
        this.add(totalRowContainer);
    }
}
