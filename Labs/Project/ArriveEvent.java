import java.util.ArrayList;
import java.util.List;

public class ArriveEvent {

    private final Event event;

    ArriveEvent(Customer customer, List<Server> servers) {
        this.event = new Event(0, customer.getArrivalTime(), customer, servers);
    }

    public Event getEvent() {
        return this.event;
    }

    public String toString() {
        return this.event.toString();
    }

    public Event execute() {
        return this.event.execute();
    }
}
