package src.code.links;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class LinkMap {

    private Map<Link, String[]> links = new HashMap<>();

    public void putLink(Link link, String[] words){
        links.put(link, words);
    }

    public String[] getWords(Link link){
        return links.get(link);
    }

    public String toString(Link link){
        String words = "";
        for (String w: links.get(link)){
            words += w + ", ";
        }
    
        return words;
    }

    public Set<Link> getKeySet(){
        return links.keySet();
    }

}
