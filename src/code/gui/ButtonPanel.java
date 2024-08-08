package src.code.gui;

import javax.swing.*;

import src.code.links.Link;

import java.awt.*;

public class ButtonPanel extends JPanel{

    private final GamePanel parentPanel;
    boolean[][] buttonArray = new boolean[4][4];

    int selected = 0;
    int correctLinks = 0;

    final int red = decideRGB(0, 148);
    final int green = decideRGB(0, 148);
    final int blue = decideRGB(0, 148);
    public Color colour = new Color(red, green, blue);

    public ButtonPanel (GamePanel parentPanel){
        super();
        this.parentPanel = parentPanel;
    }

    public boolean alreadySelected(int index){

        for (int i = 0; i < parentPanel.game.keepSelected.length; i++){
            if (parentPanel.game.keepSelected[i] != null && 
                parentPanel.game.keepSelected[i].equals(parentPanel.game.currentGameWords.get(index))){
                return true;
            }
        }
        return false;
    }

    protected void paintComponent(Graphics g){
        int pWidth = this.getWidth();
        int pHeight = this.getHeight();

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Font fontFor16Words = new Font("Calibri", Font.PLAIN, pWidth/40);
        Font fontForLinks = new Font("Calibri", Font.BOLD, pWidth/40);
        g2d.setFont(fontFor16Words);
        FontMetrics fmWords = g2d.getFontMetrics(fontFor16Words);
        FontMetrics fmLinks = g2d.getFontMetrics(fontForLinks);
        

        int index = 0;
        for (int i = 0; i < 4; i++){
            for (int j = correctLinks; j < 4; j++){

                if (buttonArray[j][i]){
                    g2d.setColor(Color.lightGray);
                } else {
                    g2d.setColor(Color.white);
                }
                g2d.fillRoundRect(i*(pWidth/4), j*(pHeight/6), pWidth/4, pHeight/6, 30, 30);
                g2d.setColor(Color.black);
                g2d.drawRoundRect(i*(pWidth/4), j*(pHeight/6), pWidth/4, pHeight/6, 30, 30);
                
                g2d.setFont(fontFor16Words);
                if (index < parentPanel.game.currentGameWords.size()){
                    // redraw words
                    String string = (parentPanel.game.currentGameWords.get(index));
                    if (fmWords.stringWidth(string) > pWidth/4){
                        drawLongString(g2d, string, i, j);
                    } else {
                        g2d.drawString(string, (pWidth/8 + (pWidth/4)*i) - fmWords.stringWidth(string)/2, pHeight/12 + j*(pHeight/6));
                    }
                    
                }
                index++;

            }
        }

        if (correctLinks > 0){
            for (int i = 0; i < correctLinks; i++){

                int redToUse = red+(i*((red/4)));
                int greenToUse = green+(i*((green/4)));
                int blueToUse = blue+(i*((blue/4)));

                g2d.setColor(new Color(redToUse, greenToUse, blueToUse));
                g2d.fillRoundRect(0, i*(pHeight/6), 3*(pWidth/4) + pWidth/4, pHeight/6, 30, 30);
                g2d.setColor(Color.black);
                g2d.drawRoundRect(0, i*(pHeight/6), 3*(pWidth/4) + pWidth/4, pHeight/6, 30, 30);

                for (int j = 0; j < 4; j++){
                    if (parentPanel.game.fourLinkNums[j][1] == (i+1)){
                        String linkString = parentPanel.game.allGameLinks.get(parentPanel.game.fourLinkNums[j][0]).toString() + ":";
                        String wordsString = getLinkWords(parentPanel.game.allGameLinks.get(parentPanel.game.fourLinkNums[j][0]));

                        g2d.setColor(Color.white);

                        g2d.setFont(fontForLinks);
                        g2d.drawString(linkString, (pWidth/4) + pWidth/4 - fmLinks.stringWidth(linkString)/2, pHeight/12 + i*(pHeight/6) - (pHeight/60));
                        g2d.setFont(fontFor16Words);
                        g2d.drawString(wordsString, (pWidth/4) + pWidth/4 - fmWords.stringWidth(wordsString)/2, pHeight/12 + i*(pHeight/6) + pHeight/60);
                    }
                }
                
            }
            
        }
        
    }

    public void drawLongString(Graphics g2d, String string, int i, int j){
        int pWidth = this.getWidth();
        int pHeight = this.getHeight();

        Font fontFor16Words = new Font("Calibri", Font.PLAIN, pWidth/40);
        FontMetrics fmWords = g2d.getFontMetrics(fontFor16Words);

        String[] sentence = string.split(" ");
        String string1 = "";
        String string2 = "";

        int test = sentence.length/2;

        for (int n = 0; n < test+1; n++){
            string1 += sentence[n] + " ";
        }
        for (int n = test+1; n < sentence.length; n++){
            string2 += sentence[n] + " ";
        }

        g2d.drawString(string1, (pWidth/8 + (pWidth/4)*i) - fmWords.stringWidth(string1)/2, pHeight/12 + j*(pHeight/6) - (pHeight/60));
        g2d.drawString(string2, (pWidth/8 + (pWidth/4)*i) - fmWords.stringWidth(string2)/2, pHeight/12 + j*(pHeight/6) + (pHeight/60));
        

    }

    public int decideRGB(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getLinkWords(Link link){
        return (parentPanel.game.getLinkMap().toString(link));
    }

    public void buttonPressed(int x, int y){

        int bpSmallestX = parentPanel.windowWidth/4;
        int bpSmallestY = parentPanel.windowHeight/5;
        int boxWidth = this.getWidth()/4;
        int boxHeight = this.getHeight()/6;

        if (parentPanel.livesPanel.numOfLives > 0){
            for (int i = 0; i < 4; i++){
                for (int j = correctLinks; j < 4; j++){
                    if (x >= bpSmallestX + i*boxWidth && x < bpSmallestX + (i+1)*boxWidth
                        && y >= bpSmallestY + j*boxHeight && y < bpSmallestY + (j+1)*boxHeight){
                        if (buttonArray[j][i]){
                            buttonArray[j][i] = false;
                            selected --;
                        } else {
                            if (selected < 4){
                                buttonArray[j][i] = true;
                                selected ++;
                            }
                        }
                    }

                }
            }
        }
        
    }

    public void resetClickedButtons(){

        if (parentPanel.buttonPanel.selected == 0){
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    buttonArray[j][i] = false;
                }
            }
        } else {

            int index = 0;
            for (int i = 0; i < 4; i++){
                for (int j = correctLinks; j < 4; j++){
                    buttonArray[j][i] = alreadySelected(index);
                    index++;
                }
            }

        }
        

    }

}
