package finalAssigments.HangMan.View;

import javax.swing.*;
import java.awt.*;

public class LeaderBoardView extends JPanel {

    public LeaderBoardView() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        // TODO REPLACE DATA WITH DATA FROM DB
        String[] players = {"Alice - 10", "Bob - 8", "Charlie - 6"};
        JList<String> leaderBoardList = new JList<>(players);

        JScrollPane scrollPane = new JScrollPane(leaderBoardList);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
