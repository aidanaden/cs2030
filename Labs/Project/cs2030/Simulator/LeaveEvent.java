import java.util.ArrayList;
import java.util.List;

public class LeaveEvent extends Event {
    
    LeaveEvent(Double startTime, Customer customer, List<Server> servers) {
        super(startTime, customer, servers);
    }

    @Override
    public String toString() {

        int serverLastIndex = getServers().size() - 1;
        int selectedServerId = getServers().get(serverLastIndex).getIdentifier();
        
        return String.format("%.3f %d leaves", getStartTime(), getCustomer().getId());
    }

    @Override
    public Event execute() {
        return null;
    }
}
