import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
