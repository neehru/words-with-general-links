package src.code;

import javax.swing.*;

import src.code.gui.GamePanel;


public class GameFrame extends JFrame{
    
    public GameFrame(GamePanel panel){
        setTitle("Words With General Links");
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);

    }

}