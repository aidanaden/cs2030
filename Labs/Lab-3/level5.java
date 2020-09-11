public class level5 {

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
}
