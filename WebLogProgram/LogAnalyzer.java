
/**
 * This class analyzes the different parts of webiste log entries.
 * It checks for unique ip address visited, number of access in a day or month.
 * 
 * @author Shreyam Duttagupta
 * @version 3rd July 2017
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) 
             records.add(WebLogParser.parseEntry(line));
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIps = new ArrayList<>();
         for(LogEntry le : records){
             String s = le.getIpAddress();
             if(!uniqueIps.contains(s))
                uniqueIps.add(s);
         }
        return uniqueIps.size();
     } 
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int number = le.getStatusCode();
             if(number> num)
                System.out.println(le);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> unique = new ArrayList<>();
         for(LogEntry le : records){
             Date d = le.getAccessTime();
             String str = d.toString();
             if(str.contains(someday)){
                String s = le.getIpAddress();
                if(!unique.contains(s))
                unique.add(s);
                }
         }
        return unique;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> unique = new ArrayList<>();
         for(LogEntry le : records){
             int number = le.getStatusCode();
             if(number >= low && number <= high){
                String s = le.getIpAddress();
                if(!unique.contains(s))
                unique.add(s);
                }
            }  
            return unique.size();
         }
         
     public HashMap<String, Integer> countVisitsPerIP(){
          HashMap<String, Integer> counts = new HashMap<String, Integer>();
          for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                counts.put(ip,1);
             }else
                counts.put(ip, counts.get(ip)+1);
            }
          return counts;
     }
     
      private HashMap<String, Integer> countVisitsPerIP(String day) {
    	 HashMap<String, Integer> map = new HashMap<String, Integer>();
    	 
    	 for (LogEntry le : records) {
    		 if (!getDay(le).equals(day)) continue;
    		 
    		 String ip = le.getIpAddress();
    		 if (!map.keySet().contains(ip)) map.put(ip, 1);
    		 else map.put(ip, map.get(ip)+1);
    	 }
    	 return map;
    	 
     }
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 0;
         for(String s : counts.keySet()){
            if(counts.get(s) > max){
                max = counts.get(s); 
            }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> maxOcc = new ArrayList<String>();
        int max = mostNumberVisitsByIP(counts);
        for(String s : counts.keySet()){
            if(counts.get(s) == max){
                maxOcc.add(s); 
            }
         }
        return maxOcc;
     }
     
      private String getDay(LogEntry le) {
    	 String date = le.getAccessTime().toString();
    	 // Assuming that day is in the same place
    	 return date.substring(4, 10);
     }
     
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
    	 HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    	 
    	 for (LogEntry le : records) {
    		 String day = getDay(le);
    		 String ip = le.getIpAddress();
    		 
    		 if (!map.containsKey(day)) {  
    			 ArrayList<String> list = new ArrayList<String>();
    			 list.add(ip);
    			 map.put(day, list);
    		 }
    		 else {
    			  map.get(day).add(ip);
    		 }
    	 }
    	 return map;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
    	 int maxSize = 0;
    	 String most = null;
    	 
    	 for (String day : map.keySet()) {
    		 int size = map.get(day).size();
    		 if (size>maxSize) {
    			 maxSize = size;
    			 most = day;
    		 }
    	 }
    	 return most;
     }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(String day) {
    	 // output
    	 ArrayList<String> list = new ArrayList<String>();
    	 HashMap<String, Integer> visits = countVisitsPerIP(day);
    	 
    	 // find max visits count
    	 int maxCount = 0;
    	 for (int count : visits.values()) 
    		 if (count > maxCount) maxCount = count;
    	 
    	 // fill output list
    	 for (String ip : visits.keySet())
    		 if (visits.get(ip) == maxCount) list.add(ip);
    	 
    	 return list;
     }
}





















