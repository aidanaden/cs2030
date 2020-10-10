import java.util.ArrayList;
import java.util.List;

public class ServeEvent extends Event {

    ServeEvent(double serviceStartTime, Customer customer, List<Server> servers) {
        super(serviceStartTime, customer, servers);
    }

    @Override
    public String toString() {

        int serverLastIndex = getServers().size() - 1;
        int selectedServerId = getServers().get(serverLastIndex).getIdentifier();
        
        return String.format("%.3f %d served by %d", 
                                getStartTime(), getCustomer().getId(), 
                                selectedServerId);
    }

    @Override
    public Event execute() {

        int lastIndex = getServers().size() - 1;
        Server selectedServer = getServers().get(lastIndex);

        double nextAvailableTime = selectedServer.getNextAvailableTime();

        ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), 
                                                                        selectedServer, 
                                                                        true, 
                                                                        false, 
                                                                        nextAvailableTime);

        return new DoneEvent(super.getStartTime() + 1.0, getCustomer(), updatedServers);
    }
}