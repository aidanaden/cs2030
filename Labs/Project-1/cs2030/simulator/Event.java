package cs2030.simulator;

import java.util.Optional;
import java.util.function.Function;

public class Event {

    private final Function<Shop, Pair<Shop, Event>> func;
    private final double startTime;
    private final Customer customer;
    private final Optional<Integer> serverId;
    private final boolean isHuman;
    private final int serverMainId;

    Event(Function<Shop, Pair<Shop, Event>> func, double startTime, Customer customer, Optional<Integer> serverId, boolean isHuman, int serverMainId) {
        this.func = func;
        this.startTime = startTime;
        this.customer = customer;
        this.serverId = serverId;
        this.isHuman = isHuman;
        this.serverMainId = serverMainId;
    }

    public double getStartTime() {
        return this.startTime;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public int getServerId() {
        return this.serverId.get();
    }

    public final Pair<Shop, Event> execute(Shop shop) { // declared final to avoid overriding
        return this.func.apply(shop); // func is the Function property
    }

    public boolean getIsHuman() {
        return isHuman;
    }

    public int getServerMainId() {
        return serverMainId;
    }
}
