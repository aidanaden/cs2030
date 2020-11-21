package cs2030.simulator;

import cs2030.simulator.RandomGenerator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ArriveEvent extends Event {

    ArriveEvent(Customer customer) {
    
        super(x -> {

            // HANDLE AVAILABLE EVENT
            Optional<Server> optionalAvailableServer = x.find(y -> y.isAvailable());

            if (optionalAvailableServer.isPresent()) {

                Server availableServer = optionalAvailableServer.get();
                
                    // if customer arrives BEFORE server is ready....
                if (customer.getArrivalTime() < availableServer.getNextAvailableTime()) {

                    if (availableServer instanceof SelfCheckoutServer) {

                        SelfCheckoutServer selfCheckoutServer = (SelfCheckoutServer) availableServer;

                        return new Pair<Shop, Event>(x, new ServeEvent(availableServer.getNextAvailableTime(), 
                                                                       customer,
                                                                       availableServer.getIdentifier(),
                                                                       false,
                                                                       selfCheckoutServer.getMainId()));
                    } else {

                        return new Pair<Shop, Event>(x, new ServeEvent(availableServer.getNextAvailableTime(), 
                                                                       customer,
                                                                       availableServer.getIdentifier(),
                                                                       true,
                                                                       0));
                    }
                
                } else {

                    if (availableServer instanceof SelfCheckoutServer) {

                        SelfCheckoutServer selfCheckoutServer = (SelfCheckoutServer) availableServer;

                        return new Pair<Shop, Event>(x, new ServeEvent(customer.getArrivalTime(), 
                                                                       customer,
                                                                       availableServer.getIdentifier(), 
                                                                       false, 
                                                                       selfCheckoutServer.getMainId()));

                    } else {

                        return new Pair<Shop, Event>(x, new ServeEvent(customer.getArrivalTime(), 
                                                                       customer,
                                                                       availableServer.getIdentifier(), 
                                                                       true, 
                                                                       0));
                    }
                    
                }   
            }

            if (customer.getIsGreedy()) {

                Optional<Server> greedyServer = x.getShortestQueue();

                if (greedyServer.isPresent()) {

                    Server waitingServer = greedyServer.get();
    
                    if (waitingServer instanceof SelfCheckoutServer) {
    
                        SelfCheckoutServer selfCheckoutServer = (SelfCheckoutServer) waitingServer;
    
                        return new Pair<Shop, Event>(x, new WaitEvent(customer.getArrivalTime(), 
                                                                      customer, 
                                                                      waitingServer.getIdentifier(),
                                                                      false,
                                                                      selfCheckoutServer.getMainId()));
    
                    } else {
    
                        return new Pair<Shop, Event>(x, new WaitEvent(customer.getArrivalTime(), 
                                                                      customer, 
                                                                      waitingServer.getIdentifier(),
                                                                      true,
                                                                      0));
                    }
                }
            }

            Predicate<Server> pred = y -> {
                if (y instanceof SelfCheckoutServer) {
                    return (Server.sharedWaitingCustomers.size() > 0) && (Server.sharedWaitingCustomers.size() < y.getMaxWaitingCustomers());
                } else {
                    return (y.getWaitingCustomers().size() > 0) && (y.getWaitingCustomers().size() < y.getMaxWaitingCustomers());       
                }
            };

            // HANDLE WAIT EVENT FOR MULTI CUSTOMER SERVER
            Optional<Server> optionalMultipleCustomerWaitingServer = x.find(pred);

            if (optionalMultipleCustomerWaitingServer.isPresent()) {
                
                Server waitingServer = optionalMultipleCustomerWaitingServer.get();

                if (waitingServer instanceof SelfCheckoutServer) {

                    SelfCheckoutServer selfCheckoutServer = (SelfCheckoutServer) waitingServer;

                    return new Pair<Shop, Event>(x, new WaitEvent(customer.getArrivalTime(), 
                                                                  customer, 
                                                                  waitingServer.getIdentifier(),
                                                                  false,
                                                                  selfCheckoutServer.getMainId()));

                } else {

                    return new Pair<Shop, Event>(x, new WaitEvent(customer.getArrivalTime(), 
                                                                  customer, 
                                                                  waitingServer.getIdentifier(),
                                                                  true,
                                                                  0));
                }
            }

            // HANDLE WAIT EVENT FOR SINGLE CUSTOMER SERVER
            Optional<Server> optionalSingleCustomerWaitingServer = x.find(y -> !y.getHasWaitingCustomer());

            if (optionalSingleCustomerWaitingServer.isPresent()) {

                Server waitingServer = optionalSingleCustomerWaitingServer.get();

                if (waitingServer instanceof SelfCheckoutServer) {

                    SelfCheckoutServer selfCheckoutServer = (SelfCheckoutServer) waitingServer;

                    return new Pair<Shop, Event>(x, new WaitEvent(customer.getArrivalTime(), 
                                                                  customer, 
                                                                  waitingServer.getIdentifier(),
                                                                  false,
                                                                  selfCheckoutServer.getMainId()));

                } else {

                    return new Pair<Shop, Event>(x, new WaitEvent(customer.getArrivalTime(), 
                                                                  customer, 
                                                                  waitingServer.getIdentifier(),
                                                                  true,
                                                                  0));
                }
            }

            return new Pair<Shop, Event>(x, new LeaveEvent(customer.getArrivalTime(), customer));

        }, customer.getArrivalTime(), customer, Optional.empty(), true, 0);
    }

    /**
     * Return string version of Arrive Event.
     * @return toString
     */
    public String toString() {

        String baseStr = String.format("%.3f %d", 
                                       super.getStartTime(),
                                       super.getCustomer().getId());

        if (super.getCustomer().getIsGreedy()) {
            baseStr += "(greedy)";
        }

        baseStr += " arrives";
        
        return baseStr;
    }
}
