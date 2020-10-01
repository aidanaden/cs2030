public class Car {
    private final String plateNumber;
    private final int waitingTime;
    private final Ride[] rides;

    Car(String plateNumber, int waitingTime, Ride[] rides) {
        this.plateNumber = plateNumber;
        this.waitingTime = waitingTime;
        this.rides = rides;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }

    public Ride[] getRides() {
        return this.rides;
    }

    @Override
    public String toString() {
        return this.plateNumber + String.format(" (%d mins away)", this.waitingTime);
    }
}
