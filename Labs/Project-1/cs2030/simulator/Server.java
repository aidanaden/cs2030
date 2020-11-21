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
    private final boolean isHuman;
    public static List<Customer> sharedWaitingCustomers = new ArrayList<Customer>();

    Server(int identifier, boolean isAvailable, 
            boolean hasWaitingCustomer, double nextAvailableTime) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxWaitingCustomers = 1;
        this.waitingCustomers = new ArrayList<>(1);
        this.isHuman = true;
    }

    Server(int identifier, boolean isAvailable, 
           boolean hasWaitingCustomer, double nextAvailableTime, 
           int maxWaitingCustomers) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxWaitingCustomers = maxWaitingCustomers;
        this.waitingCustomers = new ArrayList<>(maxWaitingCustomers);
        this.isHuman = true;
    }

    Server(int identifier, boolean isAvailable, 
           boolean hasWaitingCustomer, double nextAvailableTime, 
           int maxWaitingCustomers, boolean isHuman) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxWaitingCustomers = maxWaitingCustomers;
        this.waitingCustomers = new ArrayList<>(maxWaitingCustomers);
        this.isHuman = isHuman;
    }

    Server(int identifier, boolean isAvailable, 
           boolean hasWaitingCustomer, double nextAvailableTime, 
           List<Customer> waitingCustomers, int maxWaitingCustomers) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxWaitingCustomers = maxWaitingCustomers;
        this.waitingCustomers = new ArrayList<>(maxWaitingCustomers);
        this.waitingCustomers.addAll(waitingCustomers);
        this.isHuman = true;
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

    /**
     * Get number of waiting Customers.
     * @return Number of waiting Customers.
     */
    public int getNumWaitingCustomers() {
        if (!isHuman) {
            return Server.sharedWaitingCustomers.size();
        } else {
            return this.waitingCustomers.size();
        }
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
