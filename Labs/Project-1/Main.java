import cs2030.simulator.Simulator;

class Main {
    public static void main(String[] args) {

        int seed;
        int serverNum;
        int notHumanServerNum;
        int maxQueueLen;
        int customerNum;
        double arrivalRate;
        double serviceRate;
        double restRate;
        double probabilityRest;
        double probabilityGreedy;

        if (args.length == 6) {

            seed = Integer.parseInt(args[0]);
            serverNum = Integer.parseInt(args[1]);
            notHumanServerNum = 0;
            maxQueueLen = Integer.parseInt(args[2]);
            customerNum = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
            restRate = 0.0;
            probabilityRest = 0.0;
            probabilityGreedy = 0.0;

        } else if (args.length == 8) {
            
            seed = Integer.parseInt(args[0]);
            serverNum = Integer.parseInt(args[1]);
            notHumanServerNum = 0;
            maxQueueLen = Integer.parseInt(args[2]);
            customerNum = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
            restRate = Double.parseDouble(args[6]);
            probabilityRest = Double.parseDouble(args[7]);
            probabilityGreedy = 0.0;
        
        } else if (args.length == 9) {

            seed = Integer.parseInt(args[0]);
            serverNum = Integer.parseInt(args[1]);
            notHumanServerNum = Integer.parseInt(args[2]);
            maxQueueLen = Integer.parseInt(args[3]);
            customerNum = Integer.parseInt(args[4]);
            arrivalRate = Double.parseDouble(args[5]);
            serviceRate = Double.parseDouble(args[6]);
            restRate = Double.parseDouble(args[7]);
            probabilityRest = Double.parseDouble(args[8]);
            probabilityGreedy = 0.0;

        } else if (args.length == 10) {

            seed = Integer.parseInt(args[0]);
            serverNum = Integer.parseInt(args[1]);
            notHumanServerNum = Integer.parseInt(args[2]);
            maxQueueLen = Integer.parseInt(args[3]);
            customerNum = Integer.parseInt(args[4]);
            arrivalRate = Double.parseDouble(args[5]);
            serviceRate = Double.parseDouble(args[6]);
            restRate = Double.parseDouble(args[7]);
            probabilityRest = Double.parseDouble(args[8]);
            probabilityGreedy = Double.parseDouble(args[9]);

        } else {

            seed = Integer.parseInt(args[0]);
            serverNum = Integer.parseInt(args[1]);
            notHumanServerNum = 0;
            maxQueueLen = 1;
            customerNum = Integer.parseInt(args[2]);
            arrivalRate = Double.parseDouble(args[3]);
            serviceRate = Double.parseDouble(args[4]);
            restRate = 0.0;
            probabilityRest = 0.0;
            probabilityGreedy = 0.0;
        }
        

        Simulator sim = new Simulator(seed, serverNum, notHumanServerNum, maxQueueLen, customerNum, arrivalRate, serviceRate, restRate, probabilityRest, probabilityGreedy);
        sim.main();
    }
}
