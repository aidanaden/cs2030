package cs2030.simulator;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    
    /**
     * Decide prioritisation/sequence of Events.
     *
     * @return  int     Sequence of Events.    
     */
    public int compare(Event e1, Event e2) {
        
        if (e1.getStartTime() < e2.getStartTime()) {
            return -1;
        } else if (e1.getStartTime() > e2.getStartTime()) {
            return 1;
        } else {

            if (e1.getCustomer().getId() < e2.getCustomer().getId()) {
                return -1;
            } else if (e1.getCustomer().getId() > e2.getCustomer().getId()) {
                return 1;
            } else {

                if ((e1 instanceof SERVER_REST) || (e1 instanceof SERVER_BACK)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
}
