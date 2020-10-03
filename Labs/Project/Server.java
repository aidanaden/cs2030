public class Server {
    
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
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

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    @Override
    public String toString() {
        
        String returnStr = String.format("%d is ", this.identifier);

        if (isAvailable) {
            
            returnStr += "available"; 

        } else {

            returnStr += "busy";

            if (hasWaitingCustomer) {
                returnStr += String.format("; waiting customer to be served at %.3f", this.nextAvailableTime); 
            } else {
                returnStr += String.format("; available at %.3f", this.nextAvailableTime);
            }
        }
        return returnStr;
    }
}
