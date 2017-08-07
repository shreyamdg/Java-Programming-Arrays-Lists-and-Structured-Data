
/**
 * A program to determine the characters in one of Shakespeare’s plays that have the most speaking parts. 
 * Consider the play “The Tragedy of Macbeth” in the file macbeth.txt
 * 
 * @author Shreyam Duttagupta 
 * @version 10th June 2017
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    
    private ArrayList<String> myNames;
    private ArrayList<Integer> myFreqs;
    
    public CharactersInPlay(){
        myNames = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void update(String person){
        String s = person.toLowerCase();
        int index = myNames.indexOf(s);
        if (index == -1){
                myNames.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
    }
    public void findAllCharacters(){
        myNames.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        int index = 0;
        //String subs = "";
        for(String s : resource.lines()){
            index = s.indexOf(".");
            String subs= s.substring(0,index+1);
            update(subs);
            //index = 0;
            //subs = "";
        }        
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int i=0; i<myNames.size(); i++){
           if(myFreqs.get(i) > num1 && myFreqs.get(i) < num2){
            System.out.println(myFreqs.get(i) + "\t" + myNames.get(i));
        }
        } 
        
    }
    public void tester(){
        findAllCharacters();
        /*for(int i=0; i<myNames.size(); i++){
           System.out.println(myFreqs.get(i) + "\t" + myNames.get(i));
        }*/
        charactersWithNumParts(10,900);
        
    }
   
  }
