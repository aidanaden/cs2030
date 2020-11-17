package cs2030.simulator;

import java.util.Optional;

public class LeaveEvent extends Event {
    
    LeaveEvent(Double startTime, Customer customer) {
    
        super(x -> {
            return new Pair<Shop, Event>(x, null);
        }, startTime, customer, Optional.empty());
    }

    public String toString() {
        
        return String.format("%.3f %d leaves", super.getStartTime(), super.getCustomer().getId());
    }
}
