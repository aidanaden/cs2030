public class ShareARide extends Ride {
    
    ShareARide() {
        super(50, 0, 500);
    }

    @Override 
    public int computeFare(Request request) {
        int totalFare = super.computeFare(request);
        return  totalFare / request.getNumPassengers();
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}
