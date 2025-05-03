package weeksTasks.week04.GUI;

import weeksTasks.week04.CustomerOrder;
import weeksTasks.week04.CustomerOrderList;
import weeksTasks.week04.GUI.appFunctions.exportCSV;
import weeksTasks.week04.GUI.guiComponents.CustomerOrderRenderer;
import weeksTasks.week04.GUI.guiComponents.LabelTextInput;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class rightcolumn extends JPanel {

    private DefaultListModel<CustomerOrder> model;
    private ArrayList<CustomerOrder> allOrders;
    private JLabel lblTotalEarn;

    public rightcolumn() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(20, 10, 20, 30));

        JPanel searchPanel = new JPanel(new BorderLayout());
        LabelTextInput findTotalByEmail = new LabelTextInput("Search total by Email:", "Input Customer Email to find total spend example@example.com", true);
        LabelTextInput findTotalByDate = new LabelTextInput("Search total by Date:", "Input Date to find total earnings format yyyy-MM-DD", true);
        searchPanel.add(findTotalByEmail, BorderLayout.EAST);
        searchPanel.add(findTotalByDate, BorderLayout.WEST);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        CustomerOrderList orderList = new CustomerOrderList();
        orderList.loadFromDatabase();
        allOrders = new ArrayList<>(orderList);

        model = new DefaultListModel<>();
        for (CustomerOrder co : orderList) {
            model.addElement(co);  // this was CHATGPT code in hopes I will learn it and be able to do it on my own
        }
        // I found out that I just didn't know what DefaultListModel is and does. Now I do, after asking it. It says it just keeps the data to be displayed in the list.

        JList<CustomerOrder> jList = new JList<>(model);
        jList.setCellRenderer(new CustomerOrderRenderer());

        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel totalCSV = new JPanel(new BorderLayout());
        lblTotalEarn = new JLabel("Total: " + String.format("%.2f", calculateTotal(allOrders)));
        JButton btnExportCSV = new JButton("Export CSV");
        btnExportCSV.addActionListener(new exportCSV(orderList));
        totalCSV.add(lblTotalEarn, BorderLayout.WEST);
        totalCSV.add(btnExportCSV, BorderLayout.EAST);

        JPanel totalRowContainer = new JPanel(new BorderLayout());
        totalRowContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        totalRowContainer.add(totalCSV, BorderLayout.CENTER);

        findTotalByEmail.setActionListener(e -> {
            String email = findTotalByEmail.getTextInput().trim();
            if (!email.isEmpty()) {
                filterByEmail(email);
                findTotalByDate.setTextInput("");
            }
        });

        findTotalByDate.setActionListener(e -> {
            String date = findTotalByDate.getTextInput().trim();
            if (!date.isEmpty()) {
                filterByDate(date);
                findTotalByEmail.setTextInput("");
            }
        });


        this.add(searchPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(listPanel);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalStrut(10));
        this.add(totalRowContainer);
    }

    private void filterByEmail(String email) {
        model.clear();
        double total = 0.0;

        for (CustomerOrder co : allOrders) {
            if (co.getCustomer().getEmail().equals(email)) {
                model.addElement(co);
                total += co.getOrder().getAmount();
            }
        }

        lblTotalEarn.setText("Total: " + String.format("%.2f", total));
    }

    private void filterByDate(String date) {
        model.clear();
        double total = 0.0;

        for (CustomerOrder co : allOrders) {
            if (co.getOrder().getDate().toString().equals(date)) {
                model.addElement(co);
                total += co.getOrder().getAmount();
            }
        }

        lblTotalEarn.setText("Total: " + String.format("%.2f", total));
    }

    private double calculateTotal(ArrayList<CustomerOrder> orders) {
        double sum = 0.0;
        for (CustomerOrder co : orders) {
            sum += co.getOrder().getAmount();
        }
        return sum;
    }
}

