package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;

public class Server {
    
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    private final int maxWaitingCustomers;
    private final List<Customer> waitingCustomers;

    Server(int identifier, boolean isAvailable, 
            boolean hasWaitingCustomer, double nextAvailableTime) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxWaitingCustomers = 1;
        this.waitingCustomers = new ArrayList<>(1);
    }

    Server(int identifier, boolean isAvailable, 
    boolean hasWaitingCustomer, double nextAvailableTime, int maxWaitingCustomers) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxWaitingCustomers = maxWaitingCustomers;
        this.waitingCustomers = new ArrayList<>(maxWaitingCustomers);
    }

    Server(int identifier, boolean isAvailable, 
    boolean hasWaitingCustomer, double nextAvailableTime, List<Customer> waitingCustomers, int maxWaitingCustomers) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxWaitingCustomers = maxWaitingCustomers;
        this.waitingCustomers = new ArrayList<>(maxWaitingCustomers);
        this.waitingCustomers.addAll(waitingCustomers);
    }
    
    public int getIdentifier() {
        return this.identifier;
    }

    public boolean getHasWaitingCustomer() {
        return this.hasWaitingCustomer;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public List<Customer> getWaitingCustomers() {
        return waitingCustomers;
    }

    public int getMaxWaitingCustomers() {
        return maxWaitingCustomers;
    }

    @Override
    public String toString() {
        
        String returnStr = String.format("%d is ", this.identifier);

        if (isAvailable) {
            
            returnStr += "available"; 

        } else {

            returnStr += "busy";

            if (hasWaitingCustomer) {
                returnStr += String.format("; waiting customer to be served at %.3f", 
                                            this.nextAvailableTime); 
            } else {
                returnStr += String.format("; available at %.3f", 
                                            this.nextAvailableTime);
            }
        }
        return returnStr;
    }
}
