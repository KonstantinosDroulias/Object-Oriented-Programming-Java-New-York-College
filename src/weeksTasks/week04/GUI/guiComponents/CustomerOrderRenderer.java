package weeksTasks.week04.GUI.guiComponents;

import weeksTasks.week04.CustomerOrder;

import javax.swing.*;
import java.awt.*;

import static java.awt.Transparency.TRANSLUCENT;

// This was mostly CHATGPT Generated

public class CustomerOrderRenderer extends JPanel implements ListCellRenderer<CustomerOrder> {
    private JLabel lblName, lblEmail, lblAmount, lblDate;

    public CustomerOrderRenderer() {
        setLayout(new BorderLayout(10, 5));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // LEFT: ID, name, email (vertical)
        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        lblName = new JLabel();
        lblEmail = new JLabel();
        left.add(lblName);
        left.add(lblEmail);

        // RIGHT: price, date (vertical)
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblAmount = new JLabel();
        lblDate = new JLabel();
        right.add(lblAmount);
        right.add(lblDate);

        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends CustomerOrder> list, CustomerOrder co, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        lblName.setText(co.getOrder().getOrderID() + " - " + co.getCustomer().getName());
        lblEmail.setText(co.getCustomer().getEmail());

        lblAmount.setText(String.format("$%.2f", co.getOrder().getAmount()));
        lblDate.setText(co.getOrder().getDate().toString());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }

}
