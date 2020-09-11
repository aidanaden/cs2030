public class NormalCab extends Driver {
 
    NormalCab(String plateNumber, int passengerWaitingTime) {
        super(plateNumber, passengerWaitingTime, new Ride[] {new JustRide(), new TakeACab()});
    }

    @Override
    public String toString() {
        return super.toString() + "NormalCab";
    }
}
