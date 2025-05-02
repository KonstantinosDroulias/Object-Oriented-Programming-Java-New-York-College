package weeksTasks.week04.GUI;

import javax.swing.*;
import java.awt.*;

public class AppDisplay extends JFrame {

    public AppDisplay() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Coffee Shop");
        this.setLayout(new GridLayout(1, 2));
        this.add(new leftcolumn());
        this.add(new rightcolumn());

        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
    }
}
