package cs2030.simulator;
import java.util.Comparator;

public class GreedyServerComparator implements Comparator<Server> {
    
    /**
     * Decide prioritisation/sequence of Events.
     *
     * @return  int     Sequence of Events.    
     */
    public int compare(Server s1, Server s2) {
        
        if (s1.getNumWaitingCustomers() < s2.getNumWaitingCustomers()) {
            return -1;
        } else if (s1.getNumWaitingCustomers() > s2.getNumWaitingCustomers()) {
            return 1;
        } else {
            if (s1.getIdentifier() < s2.getIdentifier()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}