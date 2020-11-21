package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServeEvent extends Event {
    
    ServeEvent(double serviceStartTime, Customer customer, int serverId, boolean isHuman, int serverMainId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            if (server instanceof SelfCheckoutServer) {

                SelfCheckoutServer selfCheckoutServer = (SelfCheckoutServer) server;
                Server.sharedWaitingCustomers.remove(customer);

                Server updatedServer = new SelfCheckoutServer(server.getIdentifier(), 
                                                              selfCheckoutServer.getMainId(), 
                                                              false, 
                                                              selfCheckoutServer.getHasWaitingCustomer(), 
                                                              serviceStartTime + customer.getServiceTime(), 
                                                              server.getMaxWaitingCustomers());

                return new Pair<Shop, Event>(x.replace(updatedServer), new DoneEvent(serviceStartTime + customer.getServiceTime(), 
                                                                                     customer, 
                                                                                     serverId,
                                                                                     isHuman,
                                                                                     serverMainId));


            } else {

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
                                                                                     serverId,
                                                                                     isHuman,
                                                                                     serverMainId));
            }

            
        
        }, serviceStartTime, customer, Optional.of(serverId), isHuman, serverMainId);
    }

    /**
     * Output string of ServeEvent.
     * @return Output string of ServeEvent.
     */
    public String toString() {

        String baseStr = String.format("%.3f %d", 
                                       super.getStartTime(),
                                       super.getCustomer().getId());

        if (super.getCustomer().getIsGreedy()) {
            baseStr += "(greedy)";
        }

        baseStr += " served by ";

        if (super.getIsHuman()) {

            baseStr += String.format("server %d", super.getServerId());

        } else {

            baseStr += String.format("self-check %d", super.getServerId());
        }
        
        return baseStr;
    }
}