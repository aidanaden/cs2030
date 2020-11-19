import cs2030.simulator.Simulator;

class Main {
    public static void main(String[] args) {

        int seed;
        int serverNum;
        int maxQueueLen;
        int customerNum;
        double arrivalRate;
        double serviceRate;

        if (args.length == 6) {

            seed = Integer.parseInt(args[0]);
            serverNum = Integer.parseInt(args[1]);
            maxQueueLen = Integer.parseInt(args[2]);
            customerNum = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);

        } else {

            seed = Integer.parseInt(args[0]);
            serverNum = Integer.parseInt(args[1]);
            maxQueueLen = 1;
            customerNum = Integer.parseInt(args[2]);
            arrivalRate = Double.parseDouble(args[3]);
            serviceRate = Double.parseDouble(args[4]);
        }
        

        Simulator sim = new Simulator(seed, serverNum, maxQueueLen, customerNum, arrivalRate, serviceRate);
        sim.main();
    }
}
