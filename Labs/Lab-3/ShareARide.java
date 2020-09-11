public class ShareARide extends Ride {

    private final int fare = 50;
    private final int bookingFee = 0;
    private final int surchargeFee = 500;

    public int computeFare(Request request) {
        
        int subTotalFare = this.fare * request.getDistance() + this.bookingFee;

        if ((request.getTime() >= 600) && (request.getTime() <= 900)) {
            subTotalFare += surchargeFee;
        }

        double splitFareDouble = (double) subTotalFare / request.getPassengers();
        int splitFareInt = (int) splitFareDouble;
        //double friendlyDriverAbsorption = splitFareDouble - splitFareInt;

        return splitFareInt;
    }

    @Override 
    public String toString() {
        return "ShareARide";
    }
}
