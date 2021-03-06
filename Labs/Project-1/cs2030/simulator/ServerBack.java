package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServerBack extends Event {

    ServerBack(double serviceStartTime, boolean serverPreviousAvailable, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            List<Customer> existingWaitingCustomers = server.getWaitingCustomers();
        
            List<Customer> updatedWaitingCustomers = new ArrayList<Customer>();
            updatedWaitingCustomers.addAll(existingWaitingCustomers);

            Server updatedServer = new Server(server.getIdentifier(), 
                                              true, 
                                              server.getHasWaitingCustomer(), 
                                              server.getNextAvailableTime(),
                                              updatedWaitingCustomers,
                                              server.getMaxWaitingCustomers());

            return new Pair<Shop, Event>(x.replace(updatedServer), new DoneEvent(serviceStartTime, 
                                                                                 (updatedWaitingCustomers.size() > 0) ? updatedWaitingCustomers.get(0) : customer, 
                                                                                 serverId,
                                                                                 false,
                                                                                 0));
        
        }, serviceStartTime, customer, Optional.of(serverId), false, 0);

    }

    public String toString() {

        return String.format("%.3f returning server %d to rest.", this.getStartTime(), this.getServerId());
    }
}
