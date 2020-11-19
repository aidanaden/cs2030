package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServeEvent extends Event {

    ServeEvent(double serviceStartTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            List<Customer> existingWaitingCustomers = server.getWaitingCustomers();
        
            List<Customer> updatedWaitingCustomers = new ArrayList<Customer>();
            updatedWaitingCustomers.addAll(existingWaitingCustomers);
            updatedWaitingCustomers.remove(customer);

            // System.out.println("Updated Waiting Customers to DoneEvent: " + updatedWaitingCustomers);

            Server updatedServer = new Server(server.getIdentifier(), 
                                              false, 
                                              (updatedWaitingCustomers.size() > 0) ? true : false, 
                                              serviceStartTime + customer.getServiceTime(), 
                                              updatedWaitingCustomers,
                                              server.getMaxWaitingCustomers());

            return new Pair<Shop, Event>(x.replace(updatedServer), new DoneEvent(serviceStartTime + customer.getServiceTime(), 
                                                                                 customer, 
                                                                                 serverId));
        
        }, serviceStartTime, customer, Optional.of(serverId));

    }

    public String toString() {
        
        return String.format("%.3f %d served by server %d", 
                             super.getStartTime(), 
                             super.getCustomer().getId(), 
                             super.getServerId());
    }
}