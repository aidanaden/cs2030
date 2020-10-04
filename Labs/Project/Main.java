import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cs2030.Customer;
import cs2030.Server;
import cs2030.Event;
import cs2030.ArriveEvent;
import cs2030.CustomerComparator;
import cs2030.EventComparator;
import cs2030.Simulator;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> startTimes = new ArrayList<Double>();
        int numOfServers = sc.nextInt();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            startTimes.add(arrivalTime);
        }

        Simulator sim = new Simulator(startTimes, numOfServers);
        sim.main();
    }
}
