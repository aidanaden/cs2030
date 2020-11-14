public class DoneEvent extends Event {

    private final double serviceStartTime;
    private final Customer customer;
    private final int serverId;
    
    DoneEvent(Double serviceStartTime, Customer customer, int serverId) {

        super(x -> {
            return new Pair<Shop, Event>(x, null);
        });

        this.serviceStartTime = serviceStartTime;
        this.customer = customer;
        this.serverId = serverId;
    }

    public String toString() {
        
        return String.format("%.3f %d done serving by %d", 
                            serviceStartTime, customer.getId(), 
                            serverId);
    }
}
    