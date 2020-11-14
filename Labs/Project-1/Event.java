import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Event {

    private final Function<Shop, Pair<Shop, Event>> func;

    Event(Function<Shop, Pair<Shop, Event>> func) {
        this.func = func;
    }

    /**
     * Update the Server that is serving the current Customer.
     * @param servers List of servers.
     * @param selectedServer Server that is serving the current Customer.
     * @param isAvailable Availability of selected Server.
     * @param hasWaitingCustomer If selected Server has a waiting Customer or not.
     * @param nextAvailableTime Next available time of the selected Server.
     * @return List of servers with the updated status of the selected Server.
     */

    public ArrayList<Server> updateSelectedServerInServers(List<Server> servers, 
                                                            Server selectedServer, 
                                                            boolean isAvailable, 
                                                            boolean hasWaitingCustomer, 
                                                            double nextAvailableTime) {

        ArrayList<Server> updatedServers = new ArrayList<>();
        updatedServers.addAll(servers);
        updatedServers.remove(selectedServer);

        Server updatedSelectedServer = new Server(selectedServer.getIdentifier(), 
                                                    isAvailable, hasWaitingCustomer, 
                                                    nextAvailableTime);

        updatedServers.add(updatedSelectedServer);

        return updatedServers;
    }

    public final Pair<Shop, Event> execute(Shop shop) { // declared final to avoid overriding
        return this.func.apply(shop); // func is the Function property
    }
}
