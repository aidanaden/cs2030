package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoneEvent extends Event {
    
    DoneEvent(Double serviceStartTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            if (server.getWaitingCustomers().size() > 0) {

                List<Customer> existingWaitingCustomers = server.getWaitingCustomers();
                List<Customer> updatedWaitingCustomers = new ArrayList<Customer>();
                updatedWaitingCustomers.addAll(existingWaitingCustomers);

                Customer nextCustomer = updatedWaitingCustomers.get(0);

                Server updatedServer = new Server(server.getIdentifier(), 
                                                  false, 
                                                  (updatedWaitingCustomers.size() > 0) ? true : false, 
                                                  server.getNextAvailableTime(), 
                                                  updatedWaitingCustomers, 
                                                  server.getMaxWaitingCustomers());

                DoneEvent newDoneEvent = new DoneEvent(updatedServer.getNextAvailableTime(), 
                                                       updatedWaitingCustomers.get(0), 
                                                       serverId);

                return new Pair<Shop, Event>(x.replace(updatedServer), newDoneEvent);
            
            } else {

                DoneEvent newDoneEvent = new DoneEvent(server.getNextAvailableTime(), 
                                                       customer, 
                                                       serverId);

                return new Pair<Shop, Event>(x, newDoneEvent);
            }

            
        }, serviceStartTime, customer, Optional.of(serverId));
    }

    public String toString() {
        
        return String.format("%.3f %d done serving by server %d", 
                            super.getStartTime(), 
                            super.getCustomer().getId(), 
                            super.getServerId());
    }
}
    