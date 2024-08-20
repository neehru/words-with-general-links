package src.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import src.code.links.Link;
import src.code.links.LinkMap;

public class Game{
    private LinkMap linkMap;

    public Game (LinkMap linkMap){
        this.linkMap = linkMap;
    }

    public LinkMap getLinkMap(){
        return linkMap;
    }

    public List<Link> allGameLinks = new ArrayList<Link>();
    public int[][] fourLinkNums = new int[4][2];
    public String[] keepSelected = new String[4];
    public List<String> currentGameWords = new ArrayList<String>();
    public final List<String> finalCurrentGameWords = new ArrayList<String>();

    public void addAllLinksToList(){
        for (Link s: linkMap.getKeySet()){
            allGameLinks.add(s);
        }

        if (allGameLinks.size() < 4) {
            System.err.println("Not enough game links");
        }

        for (int i = 0; i < 4; i++){
            fourLinkNums[i][0] = allGameLinks.size();
        }

        resetKeepSelected();
    }

    public void resetKeepSelected(){

        for (int i = 0; i < keepSelected.length; i++){
            keepSelected[i] = null;
        }

    }

    public void decideFourLinks(){
        int count = 0; 
        while (count < 4){

            int numToAdd = returnRandomInt(0, allGameLinks.size());
            boolean repeat = false;
            for (int i = 0; i < count; i++){
                if (fourLinkNums[i][0] == numToAdd){
                    repeat = true;
                }
            }

            if (!repeat){
                fourLinkNums[count][0] = numToAdd;
                count++;
            }
            
        }

        for (int i = 0; i < 4; i++){
            String[] test = linkMap.getWords(allGameLinks.get(fourLinkNums[i][0]));
            for (String s: test){
                currentGameWords.add(s);
                finalCurrentGameWords.add(s);
            }
        }

        reorderCurrentGameWords();

    }

    public void reorderCurrentGameWords(){
        Collections.shuffle(currentGameWords);
    }

    public int returnRandomInt(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

}
