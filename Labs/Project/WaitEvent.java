import java.util.ArrayList;
import java.util.List;

public class WaitEvent extends Event {

    WaitEvent(Customer customer, List<Server> servers) {

        super(1, customer.getArrivalTime(), customer, servers);
    }

    @Override
    public String toString() {
        int serverLastIndex = super.getServers().size() - 1;
        int selectedServerId = super.getServers().get(serverLastIndex).getIdentifier();
        return String.format("%.3f %d waits to be served by %d", super.getStartTime(), super.getCustomer().getId(), selectedServerId);
    }
}
