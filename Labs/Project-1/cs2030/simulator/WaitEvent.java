package cs2030.simulator;

import java.util.Optional;

public class WaitEvent extends Event {

    WaitEvent(Double arriveTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            Server updatedServer = new Server(serverId, 
                                              false, 
                                              true, 
                                              server.getNextAvailableTime(), 
                                              server.getNumWaitingCustomers() + 1, 
                                              server.getMaxWaitingCustomers(), 
                                              server.getWaitingCustomerServeTimes() + customer.getServiceTime());

            return new Pair<Shop, Event>(x.replace(updatedServer), new ServeEvent(updatedServer.getNextAvailableTime() + server.getWaitingCustomerServeTimes(), customer, serverId));

        }, arriveTime, customer, Optional.of(serverId));
    }

    public String toString() {
        
        return String.format("%.3f %d waits to be served by server %d", 
                            super.getStartTime(), 
                            super.getCustomer().getId(), 
                            super.getServerId());
    }
}
