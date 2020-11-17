package cs2030.simulator;

import cs2030.simulator.RandomGenerator;
import java.util.Optional;
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

                    return new Pair<Shop, Event>(x, 
                                                 new ServeEvent(availableServer.getNextAvailableTime(), 
                                                                customer,
                                                                availableServer.getIdentifier()));
                
                } else {

                    return new Pair<Shop, Event>(x, 
                                                 new ServeEvent(customer.getArrivalTime(), 
                                                                customer,
                                                                availableServer.getIdentifier()));
                }   
            }

            // HANDLE WAIT EVENT 
            Optional<Server> optionalWaitingServer = x.find(y -> !y.getHasWaitingCustomer());

            if (optionalWaitingServer.isPresent()) {

                Server waitingServer = optionalWaitingServer.get();

                return new Pair<Shop, Event>(x, 
                                             new WaitEvent(customer.getArrivalTime(), 
                                                           customer, 
                                                           waitingServer.getIdentifier()));
            }

            return new Pair<Shop, Event>(x, new LeaveEvent(customer.getArrivalTime(), customer));

        }, customer.getArrivalTime(), customer, Optional.empty());
    }

    public String toString() {

        return String.format("%.3f %d arrives", super.getStartTime(), super.getCustomer().getId());
    }
}
