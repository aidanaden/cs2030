package cs2030.simulator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Supplier;


public class Simulator {
    
    private final Shop shop;
    private final PriorityQueue<Customer> customerQueue;
    private final PriorityQueue<Event> eventQueue;
    private final List<Event> eventSequence;
    private final RandomGenerator rand; 
    private double probabilityRest;

    /**
     * Create a Simulator object simulating a sequence of 
     * Customers and a list of Servers serving them.
     * 
     * @param arriveStartTimes Arrive times of the Customers.
     * @param serverNum Number of Servers serving the Customers.
     */
    public Simulator(int seed, int serverNum, int maxQueueLen, int customerNum, double arrivalRate, double serviceRate, double restRate, double probabilityRest) {

        this.shop = new Shop(serverNum, maxQueueLen);
        
        this.rand = new RandomGenerator(seed, arrivalRate, serviceRate, restRate);
        this.customerQueue = createCustomerQueue(customerNum);
        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.eventSequence = new ArrayList<Event>();
        this.probabilityRest = probabilityRest;
    }

	/**
     * Create a queue of Customer objects from the 
     * provided list of Arrive times.
     * @param arriveTimes Provided arrive times of the Customers.
     * @return PriorityQueue of Customer
     */

    public PriorityQueue<Customer> createCustomerQueue(int customerNum) {

        CustomerComparator customerComparator = new CustomerComparator();
        PriorityQueue<Customer> customerQueue = new PriorityQueue<Customer>(customerComparator);

        double prevStart = 0.0;

        for (int i = 0; i < customerNum; i++) {

            Customer newCustomer;
            Supplier<Double> serviceTime = () -> {

                return rand.genServiceTime();
            };

            if (i == 0) {

                newCustomer = new Customer(i + 1, 0, serviceTime);
            
            } else {

                newCustomer = new Customer(i + 1, prevStart + this.rand.genInterArrivalTime(), serviceTime);
                prevStart = newCustomer.getArrivalTime();
            }

            customerQueue.add(newCustomer);
        }

        return customerQueue;
    }

    /**
     * Create ArriveEvent for each Customer.
     * @param servers List of Servers.
     * @return ArriveEvent for a Customer.
     */
    public Event createArriveEvent() {

        Customer customer = this.customerQueue.poll();
        Event arriveEvent = new ArriveEvent(customer);

        return arriveEvent;
    }

    /**
     * Log Event to the Sequence to be printed out
     * at the end of the simulation.
     * @param event Event object.
     */
    public void logSequence(Event event) {

        // this.eventSequence.add(event);
        
        if ((event instanceof SERVER_REST) == false) {

            if ((event instanceof SERVER_BACK) == false) {

                this.eventSequence.add(event);
            }
        }
    }

    /**
     * Main logic of the Simulator program.
     * Returns the sequence of each Event.
     */
    public void main() {

        int leftCustomers = 0;
        int servedCustomers = 0;
        // int waitCustomers = 0;
        double totalWaitTime = 0.0;
        Shop latestShop = this.shop;

        Iterator<Customer> customerQueueIterator = this.customerQueue.iterator();
        Iterator<Event> eventQueueIterator = this.eventQueue.iterator();


        while (customerQueueIterator.hasNext()) {
            this.eventQueue.add(createArriveEvent());
        }
        

        while (eventQueueIterator.hasNext()) {

            Event currentEvent = this.eventQueue.poll();

            // log event
            logSequence(currentEvent);

            // if (currentEvent instanceof ArriveEvent) {
            //     System.out.println("Arriving Customer : " + currentEvent.getCustomer().getId());
            //     System.out.println(latestShop);
            // }

            if ((currentEvent instanceof LeaveEvent) == false) {

                if (currentEvent instanceof ServeEvent) {
                    servedCustomers++;
                }

                Pair<Shop, Event> pair = currentEvent.execute(latestShop);
                latestShop = pair.first();

                if (currentEvent instanceof ServeEvent) {
                    totalWaitTime += currentEvent.getStartTime() - currentEvent.getCustomer().getArrivalTime();
                }

                if ((currentEvent instanceof DoneEvent) == false) {

                    if ((currentEvent instanceof WaitEvent) == false) {

                        if ((currentEvent instanceof SERVER_BACK) == false) {

                            this.eventQueue.add(pair.second());
                        }
                    }
                
                } else {

                    Event nextEvent = pair.second();
                    int nextEventServerId = nextEvent.getServerId();

                    Server updatedServer = latestShop.find(x -> x.getIdentifier() == nextEventServerId).get();

                    boolean serverShouldRest = (this.rand.genRandomRest() < this.probabilityRest);

                    if (serverShouldRest) {

                        double serverRestTime = this.rand.genRestPeriod();

                        SERVER_REST serverRest = new SERVER_REST(updatedServer.getNextAvailableTime(), 
                                                                serverRestTime, 
                                                                nextEvent.getCustomer(), 
                                                                nextEvent.getServerId());
                                                                    
                        this.eventQueue.add(serverRest);

                        if (updatedServer.getWaitingCustomers().size() > 0) {

                            ServeEvent serveEvent = new ServeEvent(nextEvent.getStartTime() + serverRestTime, 
                                                                   nextEvent.getCustomer(), 
                                                                   nextEvent.getServerId());
                            
                            this.eventQueue.add(serveEvent);
                        }
                    
                    } else {

                        if (updatedServer.getWaitingCustomers().size() > 0) {

                            ServeEvent serveEvent = new ServeEvent(nextEvent.getStartTime(), 
                                                                nextEvent.getCustomer(), 
                                                                nextEvent.getServerId());
                            
                            this.eventQueue.add(serveEvent);
                        }
                    }
                    
                    
                }
            
            } else {

                // if its a LEAVE event
                leftCustomers++;
            }
        }

        for (Event event : eventSequence) {
            
            System.out.println(event);
        }

        double averageWaitTime = totalWaitTime / (double) (servedCustomers);

        if (Double.isNaN(averageWaitTime)) {
            averageWaitTime = 0.0;
        }

        String statStr = String.format("[%.3f %d %d]", averageWaitTime, 
                                        servedCustomers, leftCustomers);
        
        System.out.println(statStr);
    }
}
