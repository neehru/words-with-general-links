package src.code.gui;

import javax.swing.*;

import src.code.WordsWithGeneralLinks;
import src.code.links.Link;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class SelectPanel extends JPanel {

    private final GamePanel parentPanel;
    JButton submitButton;
    JButton deselectButton;
    JButton shuffleButton;
    public int correctCount;

    JButton retryButton;
    JButton newGameButton;

    public SelectPanel(GamePanel parentPanel){
        super();
        this.parentPanel = parentPanel;

        submitButton = new JButton("Submit");
        deselectButton = new JButton("Deselect all");
        shuffleButton = new JButton("Shuffle");

        retryButton = new JButton("Retry");
        newGameButton = new JButton("New game");

    }

    protected void paintComponent(Graphics g){

        // super.paintComponent(g);
        // Graphics2D g2d = (Graphics2D)g;

        if (parentPanel.buttonPanel.correctLinks == 4 || parentPanel.livesPanel.numOfLives < 1){
            shuffleButton.setVisible(false);
            deselectButton.setVisible(false);
            submitButton.setVisible(false);

            if (parentPanel.livesPanel.numOfLives < 1) {
                retryButton.setVisible(true);
            }
            newGameButton.setVisible(true);

        } else {
            shuffleButton.setVisible(true);
            deselectButton.setVisible(true);
            submitButton.setVisible(true);

            retryButton.setVisible(false);
            newGameButton.setVisible(false);

            if (parentPanel.buttonPanel.selected == 4){
                submitButton.setEnabled(true);
                submitButton.setBackground(parentPanel.buttonPanel.colour);
                submitButton.setForeground(Color.white);
            } else {
                submitButton.setEnabled(false);
                submitButton.setBackground(Color.white);
                submitButton.setForeground(Color.black);
                // make button look unselectable
            }
    
            if (parentPanel.buttonPanel.selected > 0){
                deselectButton.setEnabled(true);
                deselectButton.setBackground(parentPanel.buttonPanel.colour);
                deselectButton.setForeground(Color.white);
            } else {
                deselectButton.setEnabled(false);
                deselectButton.setBackground(Color.white);
                deselectButton.setForeground(Color.black);
            }

        }
        

        
    }

    public void addListeners(){

        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectButtonClicked();
            }
            
        });

        deselectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deselectButtonClicked();
            }
            
        });

        shuffleButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (parentPanel.buttonPanel.selected == 0){
                    parentPanel.game.reorderCurrentGameWords();
                } else{
                    saveCurrentSelectedWords();
                    parentPanel.game.reorderCurrentGameWords();
                    parentPanel.buttonPanel.resetClickedButtons();
                }
            }
            
        });

        retryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deselectButtonClicked();
                parentPanel.buttonPanel.correctLinks = 0;
                parentPanel.livesPanel.numOfLives = 4;
                for (int i = 0; i < 4; i++){
                    parentPanel.game.fourLinkNums[i][1] = 0;
                }
                parentPanel.game.currentGameWords = parentPanel.game.finalCurrentGameWords;
                parentPanel.game.reorderCurrentGameWords();
            }
            
        });

        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                WordsWithGeneralLinks.gameFrame.setVisible(false);
                WordsWithGeneralLinks.newGame();
            }
            
        });
    }

    public void saveCurrentSelectedWords(){

        parentPanel.game.resetKeepSelected();

        int index = 0;
        int arrayIndex = 0;
        for (int i = 0; i < parentPanel.game.keepSelected.length;i++){
            for (int j = parentPanel.buttonPanel.correctLinks; j < 4; j++){
                if (parentPanel.buttonPanel.buttonArray[j][i] && parentPanel.game.keepSelected[arrayIndex] == null){
                    parentPanel.game.keepSelected[arrayIndex] = parentPanel.game.currentGameWords.get(index);
                    arrayIndex++;
                    
                }
                index++;
            }

        }

        parentPanel.game.reorderCurrentGameWords();
    }

    public void removeFromListOfWords(Link link){
        String[] wordsToRemove = parentPanel.game.getLinkMap().getWords(link);

        Iterator<String> i = parentPanel.game.currentGameWords.iterator();

        while(i.hasNext()){
            String s = i.next();
            for (String w: wordsToRemove){
                if(s.equals(w)){
                    i.remove();
                }
            }
            
        }
    }


    public void selectButtonClicked(){
        if (!isCorrectLink()){
            parentPanel.livesPanel.numOfLives --;
        } 
        // if correct in other method bc has to pass correct link as param
    }

    public void deselectButtonClicked(){
        parentPanel.buttonPanel.selected = 0;
        parentPanel.buttonPanel.resetClickedButtons();
    }

    public boolean isCorrectLink(){

        String[] selectedWords = new String[4];

        int listIndex = 0;
        int arrayIndex = 0;
        for (int i = 0; i < 4; i++){
            for (int j = parentPanel.buttonPanel.correctLinks; j < 4; j++){
                if (parentPanel.buttonPanel.buttonArray[j][i]){
                    selectedWords[arrayIndex] = parentPanel.game.currentGameWords.get(listIndex);
                    arrayIndex++;
                }

                listIndex++;
            }
        }


        int biggestCorrectCount = 0;
        for (int i = 0; i < 4; i++){
            correctCount = 0;
            Link linkToCompare = parentPanel.game.allGameLinks.get(parentPanel.game.fourLinkNums[i][0]);
            String[] arrayOf4Words = parentPanel.game.getLinkMap().getWords(linkToCompare);
            for (int j = 0; j < 4; j++){
                for (int k = 0; k < 4; k++){
                    if(arrayOf4Words[j].equals(selectedWords[k])){
                        correctCount++;
                    }
                }
                if (correctCount > biggestCorrectCount){
                    biggestCorrectCount = correctCount;
                }

            }

            if (biggestCorrectCount == 4){
                parentPanel.buttonPanel.correctLinks++;
                deselectButtonClicked();
                removeFromListOfWords(linkToCompare);
                updatefourLinksArray(linkToCompare);
                parentPanel.messagePanel.ClearMessage();
                return true;
            } else if (biggestCorrectCount == 3){
                parentPanel.messagePanel.OneAwayMessage();
            } else if (biggestCorrectCount < 3){
                parentPanel.messagePanel.IncorrectMessage();
            }

        }
        return false;
    }

    public void updatefourLinksArray(Link link){
        for (int i = 0; i < parentPanel.game.fourLinkNums.length; i++){
            if (parentPanel.game.allGameLinks.get(parentPanel.game.fourLinkNums[i][0]).equals(link)){
                parentPanel.game.fourLinkNums[i][1] = parentPanel.buttonPanel.correctLinks;
            }
        }

    }

}
