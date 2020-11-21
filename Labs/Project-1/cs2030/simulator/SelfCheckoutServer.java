package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;

public class SelfCheckoutServer extends Server {

    private final int mainId;
    
    SelfCheckoutServer(int identifier, int mainId, boolean isAvailable, 
    boolean hasWaitingCustomer, double nextAvailableTime, int maxWaitingCustomers) {

        super(identifier, isAvailable, hasWaitingCustomer, nextAvailableTime, maxWaitingCustomers);
        this.mainId = mainId;
    }   

    public int getMainId() {
        return mainId;
    }

    @Override
    public boolean getHasWaitingCustomer() {
        if (Server.sharedWaitingCustomers.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
