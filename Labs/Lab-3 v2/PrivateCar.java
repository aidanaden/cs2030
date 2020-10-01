public class PrivateCar extends Car {
    PrivateCar(String plateNumber, int waitingTime) {
        super(plateNumber, waitingTime, new Ride[] {new JustRide(), new ShareARide()});
    }

    @Override
    public String toString() {
        return super.toString() + " PrivateCar";
    }
}
