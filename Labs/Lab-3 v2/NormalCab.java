import java.util.Arrays;

public class NormalCab extends Car {
    
    NormalCab(String plateNumber, int waitingTime) {
        super(plateNumber, waitingTime, new Ride[] {new JustRide(), new TakeACab()});
    }

    @Override
    public String toString() {
        return super.toString() + " NormalCab";
    }
}
