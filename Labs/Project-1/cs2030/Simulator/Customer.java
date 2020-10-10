// package cs2030.Simulator;

public class Customer {
    
    private final int id;
    private final double arrivalTime;

    /** Create Customer object.
     * 
     * @param id Id of Customer.
     * @param arrivalTime Arrival Time of Customer.
     */
    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Get ID of Customer.
     * @return ID of Customer.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get arrival time of Customer.
     * @return Arrival time of Customer.
     */

    public double getArrivalTime() {
        return this.arrivalTime;
    }
    
    @Override
    public String toString() {
        return String.format("%d arrives at %.1f", this.id, this.arrivalTime);
    }
}