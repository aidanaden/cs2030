package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServerRest extends Event {
    
    ServerRest(double serviceStartTime, double serverRestTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            List<Customer> existingWaitingCustomers = server.getWaitingCustomers();
        
            List<Customer> updatedWaitingCustomers = new ArrayList<Customer>();
            updatedWaitingCustomers.addAll(existingWaitingCustomers);

            Server updatedServer = new Server(server.getIdentifier(), 
                                              false, 
                                              server.getHasWaitingCustomer(), 
                                              serviceStartTime + serverRestTime,
                                              updatedWaitingCustomers,
                                              server.getMaxWaitingCustomers());

            return new Pair<Shop, Event>(x.replace(updatedServer), new SERVER_BACK(updatedServer.getNextAvailableTime(),
                                                                                   server.getIsAvailable(),
                                                                                   customer, 
                                                                                   serverId));
        
        }, serviceStartTime, customer, Optional.of(serverId), false, 0);

    }

    public String toString() {

        return String.format("%.3f sending server %d to rest.", this.getStartTime(), this.getServerId());
    }
}
