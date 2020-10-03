import java.util.ArrayList;
import java.util.List;

public class LeaveEvent extends Event {
    
    LeaveEvent(Customer customer, List<Server> servers) {
        super(4, customer.getArrivalTime(), customer, servers);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves", super.getStartTime(), super.getCustomer().getId());
    }
}
