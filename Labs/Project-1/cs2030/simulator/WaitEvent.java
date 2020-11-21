package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WaitEvent extends Event {

    WaitEvent(Double arriveTime, Customer customer, int serverId, boolean isHuman, int serverMainId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            if (server instanceof SelfCheckoutServer) {

                SelfCheckoutServer selfCheckoutServer = (SelfCheckoutServer) server;
                Server.sharedWaitingCustomers.add(customer);

                Server updatedSelfCheckoutServer = new SelfCheckoutServer(serverId, 
                                                                          selfCheckoutServer.getMainId(),
                                                                          false, 
                                                                          true, 
                                                                          server.getNextAvailableTime(), 
                                                                          server.getMaxWaitingCustomers());

                return new Pair<Shop, Event>(x.replace(updatedSelfCheckoutServer), new ServeEvent(server.getNextAvailableTime(), 
                                                                                                  customer, 
                                                                                                  serverId,
                                                                                                  isHuman,
                                                                                                  serverMainId));

            } else {

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
                                                                                      serverId,
                                                                                      isHuman,
                                                                                      serverMainId));
            }

            

        }, arriveTime, customer, Optional.of(serverId), isHuman, serverMainId);
    }

    /**
     * String output of WaitEvent.
     * @return String output of WaitEvent.
     */
    public String toString() {

        String baseStr = String.format("%.3f %d", 
                                       super.getStartTime(),
                                       super.getCustomer().getId());

        if (super.getCustomer().getIsGreedy()) {
            baseStr += "(greedy)";
        }

        baseStr += " waits to be served by ";

        if (super.getIsHuman()) {

            baseStr += String.format("server %d", super.getServerId());

        } else {

            baseStr += String.format("self-check %d", super.getServerMainId());
        }
        
        return baseStr;
    }
}
