
/**
 * This class checks for words in the files selected
 * 
 * @author Shreyam Duttagupta
 * @version 28th June 2017
 */


import edu.duke.FileResource;
import edu.duke.DirectoryResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    
    WordsInFiles(){
        wordMap = new HashMap<String, ArrayList<String>> ();
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fname = f.getName();
        for (String word : fr.words()){
            if(!wordMap.containsKey(word)){
                ArrayList<String> al = new ArrayList<String>();
                al.add(fname);
                wordMap.put(word,al);
            }
            else{
                ArrayList<String> al = wordMap.get(word);
				if (!al.contains(fname)) al.add(fname);
            }
                
        }
    }
    
    private void buildWordFileMap(){
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())    addWordsFromFile(f);
    }
    
    private int maxNumber(){
        int maxSize = 0;
		for (String key : wordMap.keySet()) {
			int aSize = wordMap.get(key).size();
			if (aSize>maxSize) maxSize=aSize;
		}
		return maxSize;
    }
    
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList <String> words = new ArrayList <String>();
        for (String key : wordMap.keySet()) {
			if(wordMap.get(key).size() == number){
			    words.add(key);
			 }    
        }
        return words;
    }
    
    private void printFilesIn(String word){
        System.out.println("Files containing "+word);
		for (String fname : wordMap.get(word)) System.out.println(fname);
    
    }
    
    void tester(){
        buildWordFileMap();
        System.out.println("Number of words in 5 files: " 
		+ wordsInNumFiles(7).size());
		// number of words that appear in 4 files
		System.out.println("Number of words in 2 files: " 
		+ wordsInNumFiles(2).size());
		
		// files with "laid"
		printFilesIn("sea");
		
		// files with "tree"
		printFilesIn("tree");
    
    }
}
