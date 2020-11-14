public class LeaveEvent extends Event {

    private final double startTime;
    private final Customer customer;
    
    LeaveEvent(Double startTime, Customer customer) {
    
        super(x -> {
            return new Pair<Shop, Event>(x, null);
        });

        this.startTime = startTime;
        this.customer = customer;
    }

    public String toString() {
        
        return String.format("%.3f %d leaves", startTime, customer.getId());
    }
}
