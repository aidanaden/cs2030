public class Request {
    private final int distance;
    private final int numPassengers;
    private final int time;

    Request(int distance, int numPassengers, int time) {
        this.distance = distance;
        this.numPassengers = numPassengers;
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }
    
    public int getNumPassengers() {
        return numPassengers;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", this.distance, this.numPassengers, this.time);
    }
}
