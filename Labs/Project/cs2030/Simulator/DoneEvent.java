import java.util.List;

public class DoneEvent extends Event {
    
    DoneEvent(Double startTime, Customer customer, List<Server> servers) {

        //return new Event(getStartTime() + 1.0, customer, servers);
        //super(servers.get(servers.size() - 1).getNextAvailableTime() + 1.0, customer, servers);
        super(startTime, customer, servers);
    }

    @Override
    public String toString() {

        int serverLastIndex = super.getServers().size() - 1;
        int selectedServerId = super.getServers().get(serverLastIndex).getIdentifier();
        
        return String.format("%.3f %d done serving by %d", 
                            getStartTime(), getCustomer().getId(), 
                            selectedServerId);
    }

    @Override
    public Event execute() {
        return null;
    }
}
    