package src.code.gui;

import javax.swing.*;

public class MessagePanel extends JPanel{

    private final GamePanel parentPanel;

    public MessagePanel(GamePanel parentPanel){
        super();
        this.parentPanel = parentPanel;
    }

    public void OneAwayMessage(){
        parentPanel.messageLabel.setText("One away");
        
    }

    public void IncorrectMessage(){
        parentPanel.messageLabel.setText("Incorrect");
    }

    public void ClearMessage(){
        parentPanel.messageLabel.setText("");
    }

    

}
