public class ServeEvent extends Event {

    private final double serviceStartTime;
    private final Customer customer;
    private final int serverId;

    ServeEvent(double serviceStartTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            Server updatedServer = new Server(server.getIdentifier(), true, false, server.getNextAvailableTime());

            return new Pair<Shop, Event>(x.replace(updatedServer), new DoneEvent(serviceStartTime + 1.0, customer, serverId));
        });

        this.serviceStartTime = serviceStartTime;
        this.customer = customer;
        this.serverId = serverId;
    }

    public String toString() {
        
        return String.format("%.3f %d served by %d", 
                                serviceStartTime, customer.getId(), 
                                serverId);
    }
}