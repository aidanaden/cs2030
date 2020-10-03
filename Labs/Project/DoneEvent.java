import java.util.List;

public class DoneEvent extends Event {
    
    DoneEvent(Customer customer, List<Server> servers) {

        super(3, servers.get(servers.size() - 1).getNextAvailableTime() + 1.0, customer, servers);
    }

    public String toString() {

        int serverLastIndex = super.getServers().size() - 1;
        int selectedServerId = super.getServers().get(serverLastIndex).getIdentifier();
        return String.format("%.3f %d done serving by %d", super.getStartTime(), super.getCustomer().getId(), selectedServerId);
    }
}
    