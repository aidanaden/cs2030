import java.util.ArrayList;
import java.util.List;

public class WaitEvent extends Event {

    WaitEvent(Double arriveTime, Customer customer, List<Server> servers) {

        super(arriveTime, customer, servers);
        //return new Event(customer.getArrivalTime(), customer, servers);
    }

    @Override
    public String toString() {

        int serverLastIndex = getServers().size() - 1;
        int selectedServerId = getServers().get(serverLastIndex).getIdentifier();
        
        return String.format("%.3f %d waits to be served by %d", 
                            getStartTime(), getCustomer().getId(), 
                            selectedServerId);
    }

    @Override
    public Event execute() {
        
        int lastIndex = getServers().size() - 1;
        Server selectedServer = getServers().get(lastIndex);

        double nextAvailableTime = selectedServer.getNextAvailableTime() + 1.0;

        ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), 
                                                                        selectedServer, 
                                                                        false, false, 
                                                                        nextAvailableTime);

        // if customer arrives BEFORE server is ready
        return new ServeEvent(selectedServer.getNextAvailableTime(), getCustomer(), updatedServers);
    }
}
