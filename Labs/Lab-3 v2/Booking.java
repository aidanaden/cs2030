public class Booking {
    private final Car car;
    private final Request request;

    Booking(Car car, Request request) {
        this.car = car;
        this.request = request;
    }

    public Car getCar() {
        return this.car;
    }

    public Ride computeBestRide() {

        int lowestRideIndex = -1;

        for (int i = 0; i < this.car.getRides().length; i++) {

            Ride ride = this.car.getRides()[i];

            if (i == 0) {
                
                lowestRideIndex = i;

            } else {

                if (computeRideFare(ride) < computeRideFare(this.car.getRides()[lowestRideIndex])) {

                    lowestRideIndex = i;
                }
            }
        }

        return this.car.getRides()[lowestRideIndex];
    }

    public int computeRideFare(Ride ride) {

        int rideFare;

        if (ride.getFare() == 50) {

            ShareARide shareRide = (ShareARide) ride;
            rideFare = shareRide.computeFare(this.request); 

        } else {
            rideFare = ride.computeFare(this.request);
        }

        return rideFare;
    }

    public int compareTo(Booking otherBooking) {

        if (computeRideFare(computeBestRide()) < computeRideFare(otherBooking.computeBestRide())) {
            return -1;

        } else if (computeRideFare(computeBestRide()) > computeRideFare(otherBooking.computeBestRide())) {
            return 1;

        } else {

            if (this.car.getWaitingTime() < otherBooking.car.getWaitingTime()) {
                return -1;

            } else {
                return 1;
            }
        }
    }

    @Override
    public String toString() {

        Ride selectedRide = computeBestRide();
        double rideFare = (double) computeRideFare(selectedRide) / 100;


        return String.format("$%.2f using ", rideFare) + this.car.toString() + " (" + selectedRide + ")"; 
    }
}
