
/**
 * @author Shreyam Duttagupta 
 * @version 3rd July
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        System.out.println();
        System.out.println("Number of unique IPs: " + la.countUniqueIPs());
        System.out.println();
        la.printAllHigherThanNum(400);
        System.out.println();
        ArrayList<String> uniqueIpsDay = la.uniqueIPVisitsOnDay("Sep 24");
        for(String le : uniqueIpsDay){
                System.out.println(le);
         }
        System.out.println("Size of arraylist" + uniqueIpsDay.size() );
        System.out.println("Number of unique IPs in the range: " + la.countUniqueIPsInRange(200,299));
    }
    public void testMap(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        new HashMap<String, ArrayList<String>>();
        HashMap<String, Integer> uniqueIp = la.countVisitsPerIP();
        System.out.println(uniqueIp);
        System.out.println("Max number of visit form a single IP is: " + la.mostNumberVisitsByIP(uniqueIp));
        ArrayList<String> mostVisit = la.iPsMostVisits(uniqueIp);
        System.out.println(mostVisit);
        System.out.println();
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        System.out.println(ipDays);
        System.out.println();
        System.out.println(la.dayWithMostIPVisits(ipDays));
        System.out.println();
        ArrayList<String> ipsWithmostVisit = la.iPsWithMostVisitsOnDay("Sep 29");
        System.out.println(ipsWithmostVisit);
    }
}
