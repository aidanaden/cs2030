public class Ride {

    private final int fare;
    private final int bookingFee;
    private final int surcharge;

    Ride(int fare, int bookingFee, int surcharge) {
        this.fare = fare;
        this.bookingFee = bookingFee;
        this.surcharge = surcharge;
    }

    public int getFare() {
        return this.fare;
    }

    public int computeFare(Request request) {

        int totalFare = this.fare * request.getDistance() + this.bookingFee;
        if ((request.getTime() <= 900) && (request.getTime() >= 600)) {
            totalFare += this.surcharge;
        }
        return totalFare;
    }
}