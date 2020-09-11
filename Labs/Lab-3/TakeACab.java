public class TakeACab extends Ride {

    private final int fare = 33;
    private final int bookingFee = 200;
    private final int surchargeFee = 0;

    public int computeFare(Request request) {
        
        int totalFare = this.fare * request.getDistance() + this.bookingFee;

        if ((request.getTime() >= 600) && (request.getTime() <= 900)) {
            totalFare += surchargeFee;
        }

        return totalFare;
    }

    @Override 
    public String toString() {
        return "TakeACab";
    }
}
