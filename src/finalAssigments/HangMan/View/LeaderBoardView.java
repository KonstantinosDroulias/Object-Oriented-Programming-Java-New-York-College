package finalAssigments.HangMan.View;

import finalAssigments.HangMan.Model.DAO.LeaderBoardDAO;
import finalAssigments.HangMan.Model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LeaderBoardView extends JPanel {

    public LeaderBoardView() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        LeaderBoardDAO dao = new LeaderBoardDAO();
        List<UserModel> users = dao.getAllUsers();

        // the sorting was AI Generated I don't know how to do that
        users.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        String[] playerData = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            UserModel user = users.get(i);
            playerData[i] = user.getUsername() + " - " + user.getScore();
        }

        JList<String> leaderBoardList = new JList<>(playerData);
        JScrollPane scrollPane = new JScrollPane(leaderBoardList);

        this.add(scrollPane, BorderLayout.CENTER);
    }
}
