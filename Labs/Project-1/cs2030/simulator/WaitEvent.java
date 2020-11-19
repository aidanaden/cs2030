package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WaitEvent extends Event {

    WaitEvent(Double arriveTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            List<Customer> existingWaitingCustomers = server.getWaitingCustomers();
            List<Customer> updatedWaitingCustomers = new ArrayList<Customer>(existingWaitingCustomers);
            updatedWaitingCustomers.add(customer);

            Server updatedServer = new Server(serverId, 
                                              false, 
                                              true, 
                                              server.getNextAvailableTime(), 
                                              updatedWaitingCustomers, 
                                              server.getMaxWaitingCustomers());

            return new Pair<Shop, Event>(x.replace(updatedServer), new ServeEvent(server.getNextAvailableTime(), 
                                                                                  customer, 
                                                                                  serverId));

        }, arriveTime, customer, Optional.of(serverId));
    }

    public String toString() {
        
        return String.format("%.3f %d waits to be served by server %d", 
                            super.getStartTime(), 
                            super.getCustomer().getId(), 
                            super.getServerId());
    }
}
