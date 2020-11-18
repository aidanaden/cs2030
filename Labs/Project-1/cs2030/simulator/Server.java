package cs2030.simulator;

public class Server {
    
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    private final int numWaitingCustomers;
    private final int maxWaitingCustomers;
    private final double waitingCustomerServeTimes;

    Server(int identifier, boolean isAvailable, 
            boolean hasWaitingCustomer, double nextAvailableTime) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.numWaitingCustomers = hasWaitingCustomer ? 1 : 0;
        this.maxWaitingCustomers = 1;
        this.waitingCustomerServeTimes = 0.0;
    }

    Server(int identifier, boolean isAvailable, 
    boolean hasWaitingCustomer, double nextAvailableTime, int numWaitingCustomers, int maxWaitingCustomers, double waitingCustomerServeTimes) {

        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.numWaitingCustomers = numWaitingCustomers;
        this.maxWaitingCustomers = maxWaitingCustomers;
        this.waitingCustomerServeTimes = waitingCustomerServeTimes;
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

    public int getNumWaitingCustomers() {
        return numWaitingCustomers;
    }

    public int getMaxWaitingCustomers() {
        return maxWaitingCustomers;
    }
    
    public double getWaitingCustomerServeTimes() {
        return waitingCustomerServeTimes;
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
