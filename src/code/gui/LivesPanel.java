package src.code.gui;

import javax.swing.*;

import java.awt.*;

public class LivesPanel extends JPanel {

    int numOfLives = 4;

    private final GamePanel parentPanel;

    public LivesPanel(GamePanel parentPanel){
        super();
        this.parentPanel = parentPanel;
    }

    protected void paintComponent(Graphics g){
        int pWidth = this.getWidth();
        int pHeight = this.getHeight();

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(10));

        if (parentPanel.buttonPanel.correctLinks < 4) {
            g2d.setColor(Color.black);
            for (int i = 0; i < numOfLives; i++){
                g2d.fillOval(((pWidth/2) + (pHeight/2)*i) - (3*pHeight/4 + pHeight/8), pHeight/2, pHeight/4, pHeight/4);
            }
        }
        

        if (numOfLives < 1){
            parentPanel.livesRemaining.setText("You lost :(");
            parentPanel.messagePanel.ClearMessage();
        } else {
            parentPanel.livesRemaining.setText("Lives remaining:");
        }

        if (parentPanel.buttonPanel.correctLinks == 4){
            parentPanel.livesRemaining.setText("WINNER!! :D");
            parentPanel.messagePanel.ClearMessage();
        }
    }
}
