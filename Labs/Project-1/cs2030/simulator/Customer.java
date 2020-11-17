package cs2030.simulator;

import java.util.function.Supplier;

public class Customer {
    
    private final int id;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;
    private double serviceTimeComputed;
    private boolean isComputed = false;

    /** Create Customer object.
     * 
     * @param id Id of Customer.
     * @param arrivalTime Arrival Time of Customer.
     */
    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = () -> 1.0;
    }

    Customer(int id, double arrivalTime, Supplier<Double> serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
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

    public double getServiceTime() {
        
        if (!isComputed) {
            isComputed = true;
            this.serviceTimeComputed = this.serviceTime.get().doubleValue();
        }
        return this.serviceTimeComputed;
    }
    
    @Override
    public String toString() {
        return String.format("%d arrives at %.1f", this.id, this.arrivalTime);
    }
}