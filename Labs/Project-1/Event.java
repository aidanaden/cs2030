// package cs2030.Simulator;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {
    
    private final double startTime;
    private final Customer customer;
    private final List<Server> servers;
    
    Event(double startTime, Customer customer, List<Server> servers) {
        this.startTime = startTime;
        this.customer = customer;
        this.servers = servers;
    }

    public double getStartTime() {
        return this.startTime;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public List<Server> getServers() {
        return this.servers;
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

    public abstract Event execute();
}
