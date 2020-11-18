package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoneEvent extends Event {
    
    DoneEvent(Double serviceStartTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            List<Customer> existingWaitingCustomers = server.getWaitingCustomers();
            List<Customer> updatedWaitingCustomers = new ArrayList<Customer>();

            if (existingWaitingCustomers.size() > 1) {
                updatedWaitingCustomers.addAll(existingWaitingCustomers);
                updatedWaitingCustomers.remove(0);
            }

            Server updatedServer = new Server(server.getIdentifier(), 
                                              (existingWaitingCustomers.size() > 0) ? false : true, 
                                              (updatedWaitingCustomers.size() > 0) ? true : false, 
                                              (existingWaitingCustomers.size() > 0) ? serviceStartTime + existingWaitingCustomers.get(0).getServiceTime() : serviceStartTime, 
                                              updatedWaitingCustomers, 
                                              server.getMaxWaitingCustomers(),
                                              (updatedWaitingCustomers.size() > 0) ? server.getWaitingCustomerServeTimes() - existingWaitingCustomers.get(0).getServiceTime() : server.getWaitingCustomerServeTimes());

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
    