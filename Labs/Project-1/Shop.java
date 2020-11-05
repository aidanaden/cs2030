import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Shop {
    
    private final int numServers;
    private final ArrayList<Server> servers;

    Shop(int numServers) {

        this.numServers = numServers;
        List<Server> servers = Stream.iterate(1, n -> n + 1)
                                     .limit(numServers)
                                     .map(x -> new Server(x, true, false, 0.0))
                                     .collect(Collectors.toList());

        this.servers = new ArrayList<Server>(servers);
    }

    Shop(List<Server> servers) {
        
        this.numServers = servers.size();
        this.servers = new ArrayList<Server>(servers);
    }

    public Optional<Server> find(Predicate<Server> pred) {
        
        Optional<Server> availableServer = servers.stream().filter(pred).findFirst();
        return availableServer;
    }

    public Shop replace(Server s) {

        Optional<Server> selectedServer = servers.stream().filter(x -> x.getIdentifier() == s.getIdentifier()).findFirst();
        Optional<Server> emptyServer = Optional.empty();

        List<Server> serversCopy = new ArrayList<Server>();
        serversCopy.addAll(this.servers);

        if (selectedServer !=  emptyServer) {
            
            Server openedServer = selectedServer.get();
            int openedServerIndex = this.servers.indexOf(openedServer);
            serversCopy.set(openedServerIndex, s);
        }

        Shop replacedShop = new Shop(serversCopy);
        
        return replacedShop;
    }
    
    @Override
    public String toString() {
        return this.servers.toString();
    }
}
