package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoneEvent extends Event {
    
    DoneEvent(Double serviceStartTime, Customer customer, int serverId, boolean isHuman, int serverMainId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            if (server instanceof SelfCheckoutServer) {

                SelfCheckoutServer selfCheckoutServer = (SelfCheckoutServer) server;

                if (Server.sharedWaitingCustomers.size() > 0) {

                    Customer nextCustomer = Server.sharedWaitingCustomers.get(0);

                    Server updatedServer = new SelfCheckoutServer(server.getIdentifier(), 
                                                                  selfCheckoutServer.getMainId(), 
                                                                  false, 
                                                                  selfCheckoutServer.getHasWaitingCustomer(),
                                                                  server.getNextAvailableTime(), 
                                                                  server.getMaxWaitingCustomers());

                    DoneEvent newDoneEvent = new DoneEvent(updatedServer.getNextAvailableTime(), 
                                                           nextCustomer, 
                                                           serverId,
                                                           isHuman,
                                                           serverMainId);

                    return new Pair<Shop, Event>(x.replace(updatedServer), newDoneEvent);
                
                } else {

                    Server updatedServer = new SelfCheckoutServer(server.getIdentifier(), 
                                                                  selfCheckoutServer.getMainId(), 
                                                                  true, 
                                                                  false, 
                                                                  server.getNextAvailableTime(), 
                                                                  server.getMaxWaitingCustomers());

                    DoneEvent newDoneEvent = new DoneEvent(server.getNextAvailableTime(),
                                                           customer,
                                                           serverId,
                                                           isHuman,
                                                           serverMainId);

                    return new Pair<Shop, Event>(x.replace(updatedServer), newDoneEvent);
                }

            } else {

                if (server.getWaitingCustomers().size() > 0) {

                    List<Customer> existingWaitingCustomers = server.getWaitingCustomers();
                    List<Customer> updatedWaitingCustomers = new ArrayList<Customer>();
                    updatedWaitingCustomers.addAll(existingWaitingCustomers);
    
                    Customer nextCustomer = updatedWaitingCustomers.get(0);
    
                    Server updatedServer = new Server(server.getIdentifier(), 
                                                      false, 
                                                      server.getHasWaitingCustomer(),
                                                      server.getNextAvailableTime(), 
                                                      updatedWaitingCustomers, 
                                                      server.getMaxWaitingCustomers());
    
                    DoneEvent newDoneEvent = new DoneEvent(updatedServer.getNextAvailableTime(), 
                                                           nextCustomer,
                                                           serverId,
                                                           isHuman,
                                                           serverMainId);
    
                    return new Pair<Shop, Event>(x.replace(updatedServer), newDoneEvent);
                
                } else {
    
                    Server updatedServer = new Server(server.getIdentifier(),
                                                      true,
                                                      false,
                                                      server.getNextAvailableTime(),
                                                      server.getWaitingCustomers(),
                                                      server.getMaxWaitingCustomers());
    
                    DoneEvent newDoneEvent = new DoneEvent(server.getNextAvailableTime(), 
                                                           customer, 
                                                           serverId,
                                                           isHuman,
                                                           serverMainId);
    
                    return new Pair<Shop, Event>(x.replace(updatedServer), newDoneEvent);
                }
            }

        }, serviceStartTime, customer, Optional.of(serverId), isHuman, serverMainId);
    }

    public String toString() {

        String baseStr = String.format("%.3f %d", 
                                       super.getStartTime(),
                                       super.getCustomer().getId());

        if (super.getCustomer().getIsGreedy()) {
            baseStr += "(greedy)";
        }

        baseStr += " done serving by ";

        if (super.getIsHuman()) {

            baseStr += String.format("server %d", super.getServerId());

        } else {

            baseStr += String.format("self-check %d", super.getServerId());
        }
        
        return baseStr;        
    }
}
    