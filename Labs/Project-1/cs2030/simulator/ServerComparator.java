package cs2030.simulator;

import java.util.Comparator;

public class ServerComparator implements Comparator<Server> {
    
    /**
     * Decide prioritisation/sequence of Events.
     *
     * @return  int     Sequence of Events.    
     */
    public int compare(Server s1, Server s2) {
        
        if (s1.getNextAvailableTime() < s2.getNextAvailableTime()) {
            return -1;
        } else {
            return 1;
        }
    }
}
