public class JustRide extends Ride {
    
    private final int fare = 22;
    private final int bookingFee = 0;
    private final int surchargeFee = 500;
 
    public int computeFare(Request request) {

        int totalFare = this.fare * request.getDistance() + this.bookingFee;

        if ((request.getTime() >= 600) && (request.getTime() <= 900)) {
            totalFare += surchargeFee;
        }

        return totalFare;
    }

    @Override 
    public String toString() {
        return "JustRide";
    }
}
