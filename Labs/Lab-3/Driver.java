public class Driver {

    private final String plateNumber;
    private final int passengerWaitingTime;
    private final Ride[] rideServices;

    Driver(String plateNumber, int passengerWaitingTime, Ride[] rideServices) {
        this.plateNumber = plateNumber;
        this.passengerWaitingTime = passengerWaitingTime;
        this.rideServices = rideServices;
    }

    public Ride[] getRideServices() {
        return this.rideServices;
    }

    public int getPassengerWaitingTime() {
        return this.passengerWaitingTime;
    }

    @Override
    public String toString() {
        return plateNumber + " (" + this.passengerWaitingTime + " mins away) ";
    }
}
