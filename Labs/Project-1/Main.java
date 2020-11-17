import cs2030.simulator.Simulator;

class Main {
    public static void main(String[] args) {

        int seed = Integer.parseInt(args[0]);
        int serverNum = Integer.parseInt(args[1]);
        int maxQueueLen = Integer.parseInt(args[2]);
        int customerNum = Integer.parseInt(args[3]);
        double arrivalRate = Double.parseDouble(args[4]);
        double serviceRate = Double.parseDouble(args[5]);

        Simulator sim = new Simulator(seed, serverNum, customerNum, arrivalRate, serviceRate);
        sim.main();
    }
}
