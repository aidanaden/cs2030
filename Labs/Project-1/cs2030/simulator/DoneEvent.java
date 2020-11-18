package cs2030.simulator;

import java.util.Optional;

public class DoneEvent extends Event {
    
    DoneEvent(Double serviceStartTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            Server updatedServer = new Server(server.getIdentifier(), 
                                              true, 
                                              false, 
                                              serviceStartTime, 
                                              server.getNumWaitingCustomers() - 1, 
                                              server.getMaxWaitingCustomers(),
                                              server.getWaitingCustomerServeTimes());

            return new Pair<Shop, Event>(x.replace(updatedServer), null);
            
        }, serviceStartTime, customer, Optional.of(serverId));
    }

    public String toString() {
        
        return String.format("%.3f %d done serving by server %d", 
                            super.getStartTime(), 
                            super.getCustomer().getId(), 
                            super.getServerId());
    }
}
    