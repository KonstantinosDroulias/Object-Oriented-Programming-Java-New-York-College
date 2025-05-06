package finalAssigments.StoreStorageApp.GUI.Components;

import javax.swing.*;

public class Theme extends JPanel {
    private String theme;
    private char weightMetric;
    private char currency;


    public Theme(String theme, char weightMetric, char currency) {
        this.theme = theme;
        this.weightMetric = weightMetric;
        this.currency = currency;
    }

    public String getTheme() {
        return theme;
        /* I will have the theme string go and lookup same name in theme table and apply colors from that column*/
    }

    public String getWeightMetric() {
        return String.valueOf(weightMetric);
    }

    public String getCurrency() {
        return String.valueOf(currency);
    }
}
