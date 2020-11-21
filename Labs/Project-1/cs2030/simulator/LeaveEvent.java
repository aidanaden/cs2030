package cs2030.simulator;

import java.util.Optional;

public class LeaveEvent extends Event {
    
    LeaveEvent(Double startTime, Customer customer) {
    
        super(x -> {
            return new Pair<Shop, Event>(x, null);
        }, startTime, customer, Optional.empty(), true, 0);
    }

    public String toString() {

        String baseStr = String.format("%.3f %d", 
                                       super.getStartTime(),
                                       super.getCustomer().getId());

        if (super.getCustomer().getIsGreedy()) {
            baseStr += "(greedy)";
        }

        baseStr += " leaves";
        
        return baseStr;
    }
}
