package src.code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import src.code.gui.GamePanel;
import src.code.links.*;

public class WordsWithGeneralLinks {

    static LinkMap linkMap;
    static GamePanel gamePanel;
    public static GameFrame gameFrame;

    public static void main (String[] args){

        linkMap = new LinkMap();

        // read from file here
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/resources/links.txt"));
            String line = br.readLine();
            while (line != null){
                String[] lineArray = line.split(",");
                if (lineArray.length == 5){
                    Link link = new Link(lineArray[0]);
                    String[] words = new String[4];
                    for(int i = 1; i < lineArray.length; i++){
                        words[i-1] = lineArray[i];
                    }
                    linkMap.putLink(link, words);
                    line = br.readLine();
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find file");
        } catch (IOException e){
            System.err.println("File could not be handled");
        }

        newGame();
    }

    public static void newGame(){
        Game newGame = new Game(linkMap);
        newGame.addAllLinksToList();
        newGame.decideFourLinks();

        gamePanel = new GamePanel(newGame);
        gameFrame = new GameFrame(gamePanel);

        gameFrame.setVisible(true);
    }



}