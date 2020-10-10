// package cs2030.Simulator;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Simulator {
    
    private final List<Server> servers;
    private final PriorityQueue<Customer> customerQueue;
    private final PriorityQueue<Event> eventQueue;
    private final List<Event> eventSequence;
    //private final List<Customer> customerList;

    /**
     * Create a Simulator object simulating a sequence of 
     * Customers and a list of Servers serving them.
     * 
     * @param arriveStartTimes Arrive times of the Customers.
     * @param serverNum Number of Servers serving the Customers.
     */
    public Simulator(List<Double> arriveStartTimes, int serverNum) {

        this.servers = createServers(serverNum);
        this.customerQueue = createCustomerQueue(arriveStartTimes);
        //this.customerList = createCustomerList(arriveStartTimes);
        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.eventSequence = new ArrayList<Event>();
    }

    /**
     * Create a queue of Customer objects from the 
     * provided list of Arrive times.
     * @param arriveTimes Provided arrive times of the Customers.
     * @return PriorityQueue of Customer
     */

    public PriorityQueue<Customer> createCustomerQueue(List<Double> arriveTimes) {

        CustomerComparator customerComparator = new CustomerComparator();
        PriorityQueue<Customer> customerQueue = new PriorityQueue<Customer>(customerComparator);

        for (int i = 0; i < arriveTimes.size(); i++) {

            Customer newCustomer = new Customer(i + 1, arriveTimes.get(i));
            customerQueue.add(newCustomer);
        }

        return customerQueue;
    }

    // public List<Customer> createCustomerList(List<Double> arriveTimes) {

    //     List<Customer> customerList = new ArrayList<Customer>();

    //     for (int i = 0; i < arriveTimes.size(); i++) {

    //         Customer newCustomer = new Customer(i+1, arriveTimes.get(i));
    //         customerList.add(newCustomer);
    //     }

    //     return customerList;
    // }

    /**
     * Create a list of Servers.
     * @param serverNum Number of Servers to create.
     * @return List of Servers.
     */

    public List<Server> createServers(int serverNum) {

        List<Server> servers = new ArrayList<Server>();

        for (int i = 0; i < serverNum; i++) {

            Server newServer = new Server(i + 1, true, false, 0);
            servers.add(newServer);
        }

        return servers;
    }

    /**
     * Create ArriveEvent for each Customer.
     * @param servers List of Servers.
     * @return ArriveEvent for a Customer.
     */
    public Event createArriveEvent(List<Server> servers) {

        Customer customer = this.customerQueue.poll();
        Event arriveEvent = new ArriveEvent(customer, servers);

        return arriveEvent;
    }

    /**
     * Log Event to the Sequence to be printed out
     * at the end of the simulation.
     * @param event Event object.
     */
    public void logSequence(Event event) {
        
        this.eventSequence.add(event);
    }


    /**
     * Get the amount of waiting time needed until
     * the selected Server can serve the Customer.
     * @param event Event object.
     * @return Amount of time to wait.
     */
    public double getWaitTime(Event event) {
        int lastIndex = event.getServers().size() - 1;
        double serverAvailableTime = event.getServers().get(lastIndex).getNextAvailableTime();
        double waitTime = serverAvailableTime - event.getStartTime();
        return waitTime;
    }

    /**
     * Update each ArriveEvent with the most 
     * updated status of each Server.
     * @param arriveEvent ArriveEvent to be updated.
     * @param latestServers List of updated Servers.
     * @return ArriveEvent with the list of updated Servers.
     */
    public Event updateArriveEvent(ArriveEvent arriveEvent, 
                                                    List<Server> latestServers) {

        return new ArriveEvent(arriveEvent.getCustomer(), latestServers);
    }

    /**
     * Update the status of the selected Server 
     * that's serving the current Customer in
     * the list of Servers.
     * @param updatedSelectedServer Server serving the current Customer.
     * @param servers List of Servers.
     * @return List of Servers with the selected Server updated.
     */
    public List<Server> updateSelectedServerInServers(Server updatedSelectedServer, 
                                                        List<Server> servers) {

        List<Server> updatedServers = new ArrayList<Server>();
        int selectedServerId = updatedSelectedServer.getIdentifier();

        for (int i = 0; i < servers.size(); i++) {

            Server currentServer = servers.get(i);
            
            if (currentServer.getIdentifier() == selectedServerId) {

                updatedServers.add(updatedSelectedServer);

            } else {

                updatedServers.add(currentServer);
            }
        }

        return updatedServers;
    }

    /**
     * Main logic of the Simulator program.
     * Returns the sequence of each Event.
     */
    public void main() {

        int leftCustomers = 0;
        int servedCustomers = 0;
        int waitCustomers = 0;
        double totalWaitTime = 0;
        List<Server> latestServers = new ArrayList<Server>();

        Iterator<Customer> customerQueueIterator = this.customerQueue.iterator();
        Iterator<Event> eventQueueIterator = this.eventQueue.iterator();


        while (customerQueueIterator.hasNext()) {
            this.eventQueue.add(createArriveEvent(this.servers));
        }

        latestServers.addAll(this.servers);
        

        while (eventQueueIterator.hasNext()) {

            Event currentEvent = this.eventQueue.poll();

            // log event
            logSequence(currentEvent);

            if ((currentEvent instanceof LeaveEvent) == false) {

                if (currentEvent instanceof ServeEvent) {
                    servedCustomers++;
                }

                if (currentEvent instanceof WaitEvent) {
                    waitCustomers++;
                    totalWaitTime += getWaitTime(currentEvent);
                }

                Event nextEvent;

                if (currentEvent instanceof ArriveEvent) {

                    int lastIndex = latestServers.size() - 1;
                    ArriveEvent currentArriveEvent = (ArriveEvent) currentEvent;

                    Event updatedArriveEvent = updateArriveEvent(currentArriveEvent, latestServers);

                    nextEvent = updatedArriveEvent.execute();

                    this.eventQueue.add(nextEvent);

                } else {

                    int lastIndex = currentEvent.getServers().size() - 1;
                    Server selectedServer = currentEvent.getServers().get(lastIndex);

                    latestServers = updateSelectedServerInServers(selectedServer, latestServers);
                    
                    if ((currentEvent instanceof WaitEvent) || 
                        (currentEvent instanceof ServeEvent)) {

                        nextEvent = currentEvent.execute();
                        this.eventQueue.add(nextEvent);
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

        String statStr = String.format("[%.3f %d %d]", averageWaitTime, 
                                        servedCustomers, leftCustomers);
        
        System.out.println(statStr);
    }
}
