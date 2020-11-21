package cs2030.simulator;

import java.util.function.Supplier;

public class Customer {
    
    private final int id;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;
    private double serviceTimeComputed;
    private boolean isComputed = false;
    private final boolean isGreedy;

    /** Create Customer object.
     * 
     * @param id Id of Customer.
     * @param arrivalTime Arrival Time of Customer.
     */
    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = () -> 1.0;
        this.isGreedy = false;
    }

    Customer(int id, double arrivalTime, Supplier<Double> serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.isGreedy = false;
    }

    Customer(int id, double arrivalTime, Supplier<Double> serviceTime, boolean isGreedy) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.isGreedy = isGreedy;
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

    /**
     * Get service time of Customer.
     * @return Service time of Customer.
     */
    public double getServiceTime() {
        
        if (!isComputed) {
            isComputed = true;
            this.serviceTimeComputed = this.serviceTime.get().doubleValue();
        }
        return this.serviceTimeComputed;
    }

    /**
     * Get Greedy status of Customer.
     * @return Greedy status of Customer.
     */
    public boolean getIsGreedy() {
        return this.isGreedy;
    }
    
    /**
     * String output of Customer.
     * @return String output of Customer.
     */
    @Override
    public String toString() {

        if (getIsGreedy()) {
            return String.format("%d(greedy) arrives at %.1f", this.id, this.arrivalTime);
        } else {
            return String.format("%d arrives at %.1f", this.id, this.arrivalTime);
        }
    }
}