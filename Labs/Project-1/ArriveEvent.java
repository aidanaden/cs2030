import java.util.Optional;

public class ArriveEvent extends Event {

    private final Customer customer;

    ArriveEvent(Customer customer) {
    
        super(x -> {

            // HANDLE AVAILABLE EVENT
            Optional<Server> optionalAvailableServer = x.find(y -> y.isAvailable());

            if (optionalAvailableServer.isPresent()) {

                Server availableServer = optionalAvailableServer.get();
                
                    // if customer arrives BEFORE server is ready....
                if (customer.getArrivalTime() < availableServer.getNextAvailableTime()) {

                    Server updatedAvailableServer = new Server(availableServer.getIdentifier(), 
                                                      false, 
                                                      availableServer.getHasWaitingCustomer(), 
                                                      availableServer.getNextAvailableTime() + 1.0);

                    // return new Pair<Shop, Event>(x.replace(updatedAvailableServer), 
                    //                              new ServeEvent(updatedAvailableServer.getNextAvailableTime(), 
                    //                                             customer,
                    //                                             updatedAvailableServer.getIdentifier()));
                    return new Pair<Shop, Event>(x, 
                                                 new ServeEvent(updatedAvailableServer.getNextAvailableTime(), 
                                                                customer,
                                                                updatedAvailableServer.getIdentifier()));
                
                } else {

                    Server updatedAvailableServer = new Server(availableServer.getIdentifier(),
                                                      false,
                                                      availableServer.getHasWaitingCustomer(),
                                                      customer.getArrivalTime() + 1.0);

                    // if customer arrives AFTER server is ready....
                    // return new Pair<Shop, Event>(x.replace(updatedAvailableServer), 
                    //                              new ServeEvent(customer.getArrivalTime(), 
                    //                                             customer,
                    //                                             updatedAvailableServer.getIdentifier()));
                    return new Pair<Shop, Event>(x, 
                                                 new ServeEvent(customer.getArrivalTime(), 
                                                                customer,
                                                                updatedAvailableServer.getIdentifier()));
                }   
            }

            // HANDLE WAIT EVENT 
            Optional<Server> optionalWaitingServer = x.find(y -> !y.getHasWaitingCustomer());

            if (optionalWaitingServer.isPresent()) {

                Server waitingServer = optionalWaitingServer.get();

                Server updatedWaitingServer = new Server(waitingServer.getIdentifier(),
                                                         false,
                                                         true,
                                                         waitingServer.getNextAvailableTime());

                // return new Pair<Shop, Event>(x.replace(updatedWaitingServer), 
                //                              new WaitEvent(customer.getArrivalTime(), 
                //                                            customer, 
                //                                            waitingServer.getIdentifier()));
                return new Pair<Shop, Event>(x, 
                                             new WaitEvent(customer.getArrivalTime(), 
                                                           customer, 
                                                           waitingServer.getIdentifier()));
            }

            return new Pair<Shop, Event>(x, new LeaveEvent(customer.getArrivalTime(), customer));
        });

        this.customer = customer;
    }

    public String toString() {

        return String.format("%.3f %d arrives", customer.getArrivalTime(), customer.getId());
    }
}
