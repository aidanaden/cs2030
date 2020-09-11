/open Request.java
/open Ride.java
/open JustRide.java
/open TakeACab.java
/open ShareARide.java
/open Driver.java 
/open NormalCab.java 
/open PrivateCar.java 
/open Booking.java

public Booking findBestBooking(Request request, Driver[] drivers) {

    Booking cheapestBooking = new Booking(drivers[0], request);

    for (int i=1; i<drivers.length; i++) {

        Driver currentDriver = drivers[i];
        Booking currentBooking = new Booking(currentDriver, request);

        if (currentBooking.compareTo(cheapestBooking) < 0) {

            cheapestBooking = currentBooking;   
        }
    }

    return cheapestBooking;
}