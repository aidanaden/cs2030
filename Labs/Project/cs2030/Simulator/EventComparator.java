// package cs2030.Simulator;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    
    public int compare(Event e1, Event e2) {
        
        if (e1.getStartTime() < e2.getStartTime()) {
            return -1;
        } else if (e1.getStartTime() > e2.getStartTime()) {
            return 1;
        } else {

            if (e1.getCustomer().getId() < e2.getCustomer().getId()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
