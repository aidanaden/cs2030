public class Booking {
    
    private final Driver bookingDriver;
    private final Request bookingRequest;
    private final Ride bookingRide;
    private final double bookingFare;
    private final int bookingWaitingTime;

    Booking(Driver driver, Request request) {
        this.bookingDriver = driver;
        this.bookingRequest = request;
        this.bookingRide = compareRideWithLowestFare(this.bookingDriver.getRideServices());
        this.bookingFare = (double) this.bookingRide.computeFare(this.bookingRequest) / 100; 
        this.bookingWaitingTime = this.bookingDriver.getPassengerWaitingTime();
    }

    public Ride compareRideWithLowestFare(Ride[] rides) {

        Ride cheapestRide = rides[0];

        for (int i=1; i < rides.length; i++) {
            
            Ride currentRide = rides[i];

            if (currentRide.computeFare(this.bookingRequest) < cheapestRide.computeFare(this.bookingRequest)) {
                cheapestRide = currentRide;
            }
        }

        return cheapestRide;
    }

    public int compareTo(Booking otherBooking) {

        if (this.bookingFare < otherBooking.bookingFare) {

            return -1;
        } 

        else if (this.bookingFare > otherBooking.bookingFare) {
            return 1;
        }

        else {
            
            if (this.bookingWaitingTime < otherBooking.bookingWaitingTime) {

                return -1;
            } 

            else if (this.bookingWaitingTime > otherBooking.bookingWaitingTime) {

                return 1; 
            }

            else {

                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return "$" + String.format("%.2f", this.bookingFare) + " using " + this.bookingDriver + " (" + this.bookingRide + ")";
    }
}
