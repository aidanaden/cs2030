import java.util.ArrayList

public Booking findBestBooking(Request request, Car[] cars) {

    ArrayList<Booking> bookings = new ArrayList<Booking>();

    for (Car car: cars) {

        Booking booking = new Booking(car, request);
        bookings.add(booking);
    }

    int bestBookingIndex = 0;

    for (int i = 0; i < bookings.size(); i++) {

        if (i == 0) {
            continue;
        } else {

            Booking bestBooking = bookings.get(bestBookingIndex);
            Booking currentBooking = bookings.get(i);

            if (currentBooking.compareTo(bestBooking) < 0) {
                bestBookingIndex = i;
            } 
        }
    }

    return bookings.get(bestBookingIndex);
}