package cs2030.Simulator;

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
    private final List<Customer> customerList;

    public Simulator(List<Double> arriveStartTimes, int serverNum) {
        this.servers = createServers(serverNum);
        this.customerQueue = createCustomerQueue(arriveStartTimes);
        this.customerList = createCustomerList(arriveStartTimes);
        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.eventSequence = new ArrayList<Event>();
    }

    public PriorityQueue<Customer> createCustomerQueue(List<Double> arriveTimes) {

        PriorityQueue<Customer> customerQueue = new PriorityQueue<Customer>(new CustomerComparator());

        for (int i = 0; i < arriveTimes.size(); i++) {

            Customer newCustomer = new Customer(i+1, arriveTimes.get(i));
            customerQueue.add(newCustomer);
        }

        return customerQueue;
    }

    public List<Customer> createCustomerList(List<Double> arriveTimes) {

        List<Customer> customerList = new ArrayList<Customer>();

        for (int i = 0; i < arriveTimes.size(); i++) {

            Customer newCustomer = new Customer(i+1, arriveTimes.get(i));
            customerList.add(newCustomer);
        }

        return customerList;
    }

    public List<Server> createServers(int serverNum) {

        List<Server> servers = new ArrayList<Server>();

        for (int i = 0; i < serverNum; i++) {

            Server newServer = new Server(i+1, true, false, 0);
            servers.add(newServer);
        }

        return servers;
    }

    public Event createArriveEvent(List<Server> servers) {

        Customer customer = this.customerQueue.poll();
        Event arriveEvent = new ArriveEvent(customer, servers).getEvent();

        return arriveEvent;
    }

    public void logSequence(Event event) {
        // System.out.println("Logging event");
        this.eventSequence.add(event);
    }

    public double getWaitTime(Event event) {
        int lastIndex = event.getServers().size() - 1;
        double serverAvailableTime = event.getServers().get(lastIndex).getNextAvailableTime();
        double waitTime = serverAvailableTime - event.getStartTime();
        return waitTime;
    }

    public Event updateEventWithLatestServers(Event event, List<Server> latestServers) {

        return new Event(event.getState(), event.getStartTime(), event.getCustomer(), latestServers);
    }

    public void main() {

        int leftCustomers = 0;
        int servedCustomers = 0;
        int waitCustomers = 0;
        double totalWaitTime = 0;
        List<Server> latestServers = new ArrayList<Server>();

        Iterator<Customer> customerQueueIterator = this.customerQueue.iterator();
        Iterator<Event> eventQueueIterator = this.eventQueue.iterator();


        for (Customer customer : this.customerList) {
            this.eventQueue.add(createArriveEvent(this.servers));
        }

        latestServers.addAll(this.servers);

        // get first ARRIVE event from first CUSTOMER
        // this.eventQueue.add(createArriveEvent(this.servers));

        

        while (eventQueueIterator.hasNext()) {

            Event currentEvent = this.eventQueue.poll();

            // log event
            logSequence(currentEvent);
            // System.out.println("========== NEXT ITERATION: ==========");
            // System.out.println("");
            // System.out.println("Current Event: " + currentEvent.toString());
            // System.out.println("Server of current Event: " + currentEvent.getServers().get(currentEvent.getServers().size()-1));
            // System.out.println("");

            if (currentEvent.getState() != 4) {

                if (currentEvent.getState() == 2) {
                    servedCustomers++;
                }

                if (currentEvent.getState() == 1) {
                    waitCustomers++;
                    totalWaitTime += getWaitTime(currentEvent);
                }

                Event nextEvent;

                if (currentEvent.getState() == 0) {

                    Event updatedArriveEvent = updateEventWithLatestServers(currentEvent, latestServers);
                    nextEvent = updatedArriveEvent.execute();
                    latestServers = nextEvent.getServers();

                } else {

                    nextEvent = currentEvent.execute();
                    latestServers = nextEvent.getServers();
                    
                }

                this.eventQueue.add(nextEvent);

                //System.out.println("Event is not DONE/LEAVE, executing...");
                // if event is executable (is not a DONE/LEAVE event)
                // run .execute() to get next event
                // Event nextEvent = currentEvent.execute();

                // System.out.println("Next Event: " + nextEvent.toString());
                // System.out.println("Server of next Event: " + nextEvent.getServers().get(nextEvent.getServers().size()-1));
                // System.out.println("");

                // if (customerQueueIterator.hasNext()) {
                    
                //     if (currentEvent.getState() > 0) {
                        
                //         // System.out.println("CREATING NEW ARRIVE STATE\n");
                //         Event newArriveEvent = createArriveEvent(currentEvent.getServers());
                //         // System.out.println(newArriveEvent);
                //         // System.out.println(newArriveEvent.getServers());
                //         // System.out.println("");
                //         this.eventQueue.add(newArriveEvent);
                //     }

                // }

                // add newly created event to the queue 
                // this.eventQueue.add(nextEvent);
            
            } else {
                // if its a LEAVE or DONE event, check if next customer
                // arrives before next upcoming event

                
                leftCustomers++;
                

                
                
                // if (customerQueueIterator.hasNext() && (eventQueueIterator.hasNext())) {

                //     // get next event and customer
                //     Customer nextCustomer = customerQueue.poll();
                //     Event upcomingEvent = this.eventQueue.poll();
                //     this.eventQueue.add(upcomingEvent);
                //     customerQueue.add(nextCustomer);

                //     double upcomingEventStart = upcomingEvent.getStartTime();
                    

                    // System.out.println("NEXT CUSTOMER: " + nextCustomer);
                    // System.out.println("UPCOMING EVENT START: " + upcomingEventStart);

                //     if (nextCustomer.getArrivalTime() < upcomingEventStart) {
                //         // if customer arrives before the next event starts, 
                //         // create ARRIVE event for customer
                //         // System.out.println("CREATING NEW ARRIVE STATE\n");
                //         Event newArriveEvent = createArriveEvent(currentEvent.getServers());
                //         // System.out.println(newArriveEvent);
                //         // System.out.println(newArriveEvent.getServers());
                //         // System.out.println("");
                //         this.eventQueue.add(newArriveEvent);
                    
                //     } 
                // }
            }

        }

        for (Event event : eventSequence) {
            // sequenceStr += event.toString() + System.lineSeparator();
            System.out.println(event);
        }

        double averageWaitTime = totalWaitTime / (double) (servedCustomers);

        String statStr = String.format("[%.3f %d %d]", averageWaitTime, servedCustomers, leftCustomers);

        // System.out.println(Arrays.asList(averageWaitTime, leftCustomers, servedCustomers));
        System.out.println(statStr);
    }
}
