import java.util.ArrayList;
import java.util.List;

public class ServeEvent extends Event {

    ServeEvent(double serviceStartTime, Customer customer, List<Server> servers) {
        super(2, serviceStartTime, customer, servers);
    }

    @Override
    public String toString() {
        int serverLastIndex = super.getServers().size() - 1;
        int selectedServerId = super.getServers().get(serverLastIndex).getIdentifier();
        return String.format("%.3f %d served by %d", super.getStartTime(), super.getCustomer().getId(), selectedServerId);
    }
}
