// package cs2030.Simulator;

import java.util.ArrayList;
import java.util.List;

public class ArriveEvent extends Event {

    ArriveEvent(Customer customer, List<Server> servers) {
    
        super(customer.getArrivalTime(), customer, servers);
    }

    @Override
    public String toString() {
        
        int serverLastIndex = super.getServers().size() - 1;
        int selectedServerId = super.getServers().get(serverLastIndex).getIdentifier();

        return String.format("%.3f %d arrives", getStartTime(), getCustomer().getId());
    }

    /**
     * Return the soonest available server from the list of servers.
     * @param servers List of servers 
     * @return Index of the soonest available server in the list of servers.
     */
    public int getSoonestAvailableServerIndex(List<Server> servers) {

        int nextAvailableServerIndex = 0;

        for (Server server : servers) {

            // System.out.println("SERVER TO CHOOSE: " + server);
            // System.out.print(getCustomer().getArrivalTime() - server.getNextAvailableTime());
                
            if (servers.indexOf(server) == 0) {
                continue;
                
            } else {

                double customerArrivalTime = super.getCustomer().getArrivalTime();
                double serverNextAvailableTime = server.getNextAvailableTime();

                double actualWaitTime = customerArrivalTime - serverNextAvailableTime;
                double waitTime = Math.abs(actualWaitTime);

                Server nextAvailableServer = servers.get(nextAvailableServerIndex);
                double nextAvailableServerTime = nextAvailableServer.getNextAvailableTime();

                double actualNextAvailableWaitTime = customerArrivalTime - nextAvailableServerTime;
                double nextAvailableWaitTime = Math.abs(actualNextAvailableWaitTime);
                
                int currentServerIndex = servers.indexOf(server);
                
                // get absolute diff between customer arrival time
                // and server next available time 
                // (pick server that ends sooner)
                if (waitTime < nextAvailableWaitTime) {

                    // if customer arrives before server is next available...
                    if (actualWaitTime < 0) {

                        nextAvailableServerIndex = currentServerIndex;

                    } else {
                        // if customer arrives after server is available...
                        // pick the one that has been available for longer period
                        if (actualWaitTime > actualNextAvailableWaitTime) {
    
                            nextAvailableServerIndex = currentServerIndex;
                        }
                    }
                }
            } 
        }

        return nextAvailableServerIndex;
    }

    /**
     * Get the server with a Waiting spot from the list of servers.
     * @param servers List of servers.
     * @return  Index of the server to wait for (lowest server ID).
     */

    public int getSoonestWaitingServerIndex(List<Server> servers) {

        int lowestServerIdIndex = 0;

        for (Server server : servers) {

            if (servers.indexOf(server) == 0) {
                continue;

            } else {

                int lowestServerId = servers.get(lowestServerIdIndex).getIdentifier();

                if (server.getIdentifier() < lowestServerId) {

                    lowestServerIdIndex = servers.indexOf(server);
                }
            }
        }
        
        return lowestServerIdIndex;
    }

    /**
     * Execute current event and return the next event.
     * @return Next event
     */

    @Override
    public Event execute() {

        // HANDLE AVAILABLE EVENT
        ArrayList<Server> availableServers = new ArrayList<Server>();

        for (Server server: super.getServers()) {

            if (server.getIsAvailable()) {
                availableServers.add(server);
            }
        }

        if (availableServers.size() > 0) {
            
            int soonestServerIndex = getSoonestAvailableServerIndex(availableServers);

            Server selectedServer = availableServers.get(soonestServerIndex);

            // if customer arrives BEFORE server is ready....
            if (getCustomer().getArrivalTime() < selectedServer.getNextAvailableTime()) {

                ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), 
                                                    selectedServer, false, 
                                                    selectedServer.getHasWaitingCustomer(), 
                                                    selectedServer.getNextAvailableTime() + 1.0);

                int lastIndex = updatedServers.size() - 1;

                return new ServeEvent(updatedServers.get(lastIndex).getNextAvailableTime(), 
                                        getCustomer(), updatedServers);
            
            } else {
                // if customer arrives AFTER server is ready....
                ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), 
                                                    selectedServer, false, 
                                                    selectedServer.getHasWaitingCustomer(), 
                                                    getCustomer().getArrivalTime() + 1.0);

                int lastIndex = updatedServers.size() - 1;

                return new ServeEvent(getCustomer().getArrivalTime(), 
                                        getCustomer(), updatedServers);
            }   
        }


        // HANDLE WAIT EVENT
        ArrayList<Server> waitingServers = new ArrayList<Server>();

        for (Server server: getServers()) {

            if (server.getHasWaitingCustomer() == false) {
                waitingServers.add(server);
            }
        }

        if (waitingServers.size() > 0) {

            int soonestServerIndex = getSoonestWaitingServerIndex(waitingServers);

            Server selectedServer = waitingServers.get(soonestServerIndex);

            // update selected server
            // isAvailable = false; (existing customer)
            // hasWaitingCustomer = true; (this customer)
            ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), 
                                                selectedServer, false, 
                                                true, selectedServer.getNextAvailableTime());

            // startTime for WaitEvent is always customer.arrivalTime
            return new WaitEvent(getCustomer().getArrivalTime(), 
                                    getCustomer(), updatedServers);
        }

        // HANDLE LEAVE EVENT
        return new LeaveEvent(getCustomer().getArrivalTime(), 
                                getCustomer(), getServers());
    }

}
