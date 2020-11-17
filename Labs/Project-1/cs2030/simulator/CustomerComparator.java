package cs2030.simulator;
import java.util.Comparator;

public class CustomerComparator implements Comparator<Customer> {

    /**
     * Compare Customer objects.
     *
     * @return  int     Sequence of Customers.
     */
    public int compare(Customer c1, Customer c2) {
        
        if (c1.getArrivalTime() < c2.getArrivalTime()) {
            return -1;
        } else if (c1.getArrivalTime() > c2.getArrivalTime()) {
            return 1;
        } else {
            return 0;
        }
    }
}
