package cs2030.simulator;

import java.util.Optional;

public class ServeEvent extends Event {

    ServeEvent(double serviceStartTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            Server updatedServer = new Server(server.getIdentifier(), false, false, serviceStartTime + customer.getServiceTime());

            return new Pair<Shop, Event>(x.replace(updatedServer), new DoneEvent(serviceStartTime + customer.getServiceTime(), customer, serverId));
        
        }, serviceStartTime, customer, Optional.of(serverId));

    }

    public String toString() {
        
        return String.format("%.3f %d served by server %d", 
                                super.getStartTime(), 
                                super.getCustomer().getId(), 
                                super.getServerId());
    }
}