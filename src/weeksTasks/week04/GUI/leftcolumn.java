package weeksTasks.week04.GUI;

import weeksTasks.week04.GUI.appFunctions.addOrder;
import weeksTasks.week04.GUI.guiComponents.LabelTextInput;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class leftcolumn extends JPanel {
    private LabelTextInput NameOrderLabel;
    private LabelTextInput EmailOrder;
    private LabelTextInput PriceOrderLabel;

    public leftcolumn() {
        this.setLayout(new GridLayout(2, 1));
        this.setBorder(new EmptyBorder(20, 30, 20, 10));

        JPanel addOrderPanel = new JPanel();
        addOrderPanel.setLayout(new BoxLayout(addOrderPanel, BoxLayout.Y_AXIS));
        NameOrderLabel = new LabelTextInput("Name", "Insert Customer's Name");
        EmailOrder = new LabelTextInput("Email", "Insert Customer's Email");
        PriceOrderLabel = new LabelTextInput("Amount (Format: 0.00)", "Insert Order's Price in x.00 format");
        JButton AddOrderButton = new JButton("Add Order");
        AddOrderButton.addActionListener(new addOrder(this));
        addOrderPanel.add(NameOrderLabel);
        addOrderPanel.add(EmailOrder);
        addOrderPanel.add(PriceOrderLabel);
        addOrderPanel.add(AddOrderButton);
        JPanel ImagePanel = new JPanel();
        ImagePanel.setLayout(new BoxLayout(ImagePanel, BoxLayout.Y_AXIS));
        ImageIcon logoImage = new ImageIcon("src/weeksTasks/week04/images/coffeshoplogo.png");
        JLabel logo = new JLabel(logoImage);
        ImagePanel.add(logo);

        this.add(addOrderPanel);
        this.add(ImagePanel);
    }

    public LabelTextInput getNameOrderLabel() {
        return NameOrderLabel;
    }
    public LabelTextInput getEmailOrder() {
        return EmailOrder;
    }
    public LabelTextInput getPriceOrderLabel() {
        return PriceOrderLabel;
    }

}
