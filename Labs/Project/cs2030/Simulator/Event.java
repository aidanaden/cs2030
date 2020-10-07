// package cs2030.Simulator;

import java.util.ArrayList;
import java.util.List;

public class Event {
    
    private final double startTime;
    private final Customer customer;
    private final List<Server> servers;
    private final int state;
    
    Event(int state, double startTime, Customer customer, List<Server> servers) {
        this.startTime = startTime;
        this.customer = customer;
        this.servers = servers;
        this.state = state;
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

    public int getState() {
        return this.state;
    }

    public String toString() {

        int serverLastIndex = getServers().size() - 1;
        int selectedServerId = getServers().get(serverLastIndex).getIdentifier();

        if (this.state == 0) {
            // handle ARRIVE event
            return String.format("%.3f %d arrives", getStartTime(), getCustomer().getId());

        } else if (this.state == 1) {
            // handle WAIT event
            return String.format("%.3f %d waits to be served by %d", getStartTime(), getCustomer().getId(), selectedServerId);

        } else if (this.state == 2) {
            // handle SERVE event
            return String.format("%.3f %d served by %d", getStartTime(), getCustomer().getId(), selectedServerId);

        } else if (this.state == 3) {
            // handle DONE event
            return String.format("%.3f %d done serving by %d", getStartTime(), getCustomer().getId(), selectedServerId);

        } else {
            // handle LEAVE event
            return String.format("%.3f %d leaves", getStartTime(), getCustomer().getId());
        }        
    }

    public ArrayList<Server> updateSelectedServerInServers(List<Server> servers, Server selectedServer, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {

        ArrayList<Server> updatedServers = new ArrayList<>();
        updatedServers.addAll(servers);
        updatedServers.remove(selectedServer);

        Server updatedSelectedServer = new Server(selectedServer.getIdentifier(), isAvailable, hasWaitingCustomer, nextAvailableTime);
        updatedServers.add(updatedSelectedServer);

        return updatedServers;
    }

    public Event returnWaitEvent(Customer customer, List<Server> servers) {
        return new Event(1, customer.getArrivalTime(), customer, servers);
    }

    public Event returnServeEvent(double startTime, Customer customer, List<Server> servers) {
        return new Event(2, startTime, customer, servers);
    }

    public Event returnDoneEvent(Customer customer, List<Server> servers) {
        return new Event(3, getStartTime() + 1.0, customer, servers);
    }

    public Event returnLeaveEvent(Customer customer, List<Server> servers) {
        return new Event(4, customer.getArrivalTime(), customer, servers);
    }

    public Event execute() {

        if (this.state == 0) {
            // handle ARRIVE event

            return arriveExecute();

        } else if (this.state == 1) {
            // handle WAIT event

            return waitExecute();            
        
        } else if (this.state == 2) {
            // handle SERVE event

            return serveExecute();

        } else {
            // handle DONE & LEAVE event
            return null;
        }
    }

    public Event waitExecute() {
        
        int lastIndex = getServers().size() - 1;
        Server selectedServer = getServers().get(lastIndex);

        ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), selectedServer, false, false, selectedServer.getNextAvailableTime() + 1.0);

        // if customer arrives BEFORE server is ready
        return returnServeEvent(selectedServer.getNextAvailableTime(), getCustomer(), updatedServers);
    }

    public Event serveExecute() {

        int lastIndex = getServers().size() - 1;
        Server selectedServer = getServers().get(lastIndex);

        ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), selectedServer, true, false, selectedServer.getNextAvailableTime());

        return returnDoneEvent(getCustomer(), updatedServers);
    }

    public int getSoonestAvailableServerIndex(List<Server> servers) {

        int soonestAvailableServerIndex = 0;

        for (Server server : servers) {

            // System.out.println("SERVER TO CHOOSE: " + server);
            // System.out.print(getCustomer().getArrivalTime() - server.getNextAvailableTime());
                
            if (servers.indexOf(server) == 0) {
                continue;
                
            } else {

                double actualWaitTime = getCustomer().getArrivalTime() - server.getNextAvailableTime();
                double waitTime = Math.abs(actualWaitTime);

                Server soonestAvailableServer = servers.get(soonestAvailableServerIndex);
                double actualSoonestAvailableWaitTime = getCustomer().getArrivalTime() - soonestAvailableServer.getNextAvailableTime();
                double soonestAvailableWaitTime = Math.abs(actualSoonestAvailableWaitTime);
                
                int currentServerIndex = servers.indexOf(server);
                
                // get absolute diff between customer arrival time
                // and server next available time 
                // (pick server that ends sooner)
                if (waitTime < soonestAvailableWaitTime) {

                    // if customer arrives before server is next available...
                    if (actualWaitTime < 0) {

                        soonestAvailableServerIndex = currentServerIndex;

                    } else {
                    // if customer arrives after server is available...
                    // pick the one that has been available for longer period
                        if (actualWaitTime > actualSoonestAvailableWaitTime) {
    
                            soonestAvailableServerIndex = currentServerIndex;
                        }
                    }
                }
            } 
        }

        return soonestAvailableServerIndex;
    }

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

    public Event arriveExecute() {

        // HANDLE AVAILABLE EVENT
        ArrayList<Server> availableServers = new ArrayList<Server>();

        for (Server server: getServers()) {

            if (server.getIsAvailable()) {
                availableServers.add(server);
            }
        }

        if (availableServers.size() > 0) {
            
            int soonestServerIndex = getSoonestAvailableServerIndex(availableServers);

            Server selectedServer = availableServers.get(soonestServerIndex);

            // if customer arrives BEFORE server is ready....
            if (getCustomer().getArrivalTime() < selectedServer.getNextAvailableTime()) {

                ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), selectedServer, false, selectedServer.getHasWaitingCustomer(), selectedServer.getNextAvailableTime() + 1.0);
                int lastIndex = updatedServers.size() - 1;

                return returnServeEvent(updatedServers.get(lastIndex).getNextAvailableTime(), getCustomer(), updatedServers);
            
            } else {
            // if customer arrives AFTER server is ready....
                ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), selectedServer, false, selectedServer.getHasWaitingCustomer(), getCustomer().getArrivalTime() + 1.0);
                int lastIndex = updatedServers.size() - 1;

                return returnServeEvent(getCustomer().getArrivalTime(), getCustomer(), updatedServers);
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
            ArrayList<Server> updatedServers = updateSelectedServerInServers(getServers(), selectedServer, false, true, selectedServer.getNextAvailableTime());

            // startTime for WaitEvent is always customer.arrivalTime
            return returnWaitEvent(getCustomer(), updatedServers);
        }


        // HANDLE LEAVE EVENT
        return returnLeaveEvent(getCustomer(), getServers());
    }
}
