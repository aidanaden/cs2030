public class WaitEvent extends Event {

    private final double arriveTime;
    private final Customer customer;
    private final int serverId;

    WaitEvent(Double arriveTime, Customer customer, int serverId) {

        super(x -> {

            Server server = x.find(y -> y.getIdentifier() == serverId).get();

            Server updatedServer = new Server(serverId, false, false, server.getNextAvailableTime() + 1.0);

            return new Pair<Shop, Event>(x.replace(updatedServer), new ServeEvent(server.getNextAvailableTime(), customer, serverId));
        });
        

        this.arriveTime = arriveTime;
        this.customer = customer;
        this.serverId = serverId;
    }

    public String toString() {
        
        return String.format("%.3f %d waits to be served by %d", 
                            arriveTime, customer.getId(), 
                            serverId);
    }
}
