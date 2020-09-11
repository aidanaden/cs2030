public class PrivateCar extends Driver {
 
    PrivateCar(String plateNumber, int passengerWaitingTime) {
        super(plateNumber, passengerWaitingTime, new Ride[] {new JustRide(), new ShareARide()});
    }

    @Override
    public String toString() {
        return super.toString() + "PrivateCar";
    }
}
