package finalAssigments.HangMan.View;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;

public class HangManDraw extends JPanel {
    private int livesLeft = 6;

    public void setLivesLeft(int lives) {
        this.livesLeft = lives;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGallows(g);
        drawHangman(g, livesLeft);
    }

    private void drawGallows(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(250, 250, 350, 250); // rope
        g.drawLine(300, 50, 300, 250); // extended thing
        g.drawLine(300, 50, 400, 50); // base
        g.drawLine(400, 50, 400, 80); // pole
    }

    private void drawHangman(Graphics g, int livesLeft) {
        g.setColor(Color.BLACK);
        if (livesLeft <= 5) {
            g.drawOval(380, 80, 40, 40);
            // head
        }
        if (livesLeft <= 4) {
            g.drawLine(400, 120, 400, 180);
            // body
        }
        if (livesLeft <= 3) {
            g.drawLine(400, 140, 370, 160);
            // keft hand
        }
        if (livesLeft <= 2) {
            g.drawLine(400, 140, 430, 160);
            //  Right hand
        }
        if (livesLeft <= 1) {
            g.drawLine(400, 180, 380, 220);
            // left foot
        }
        if (livesLeft == 0) {
            g.drawLine(400, 180, 420, 220);
            // right foot
        }
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void decreaseLivesLeft() {
        this.livesLeft--;
        repaint();
    }
}

