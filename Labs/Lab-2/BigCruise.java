class BigCruise extends Cruise {


    BigCruise(String identifier, int arrivalTime, int cruiseLength, int numPassengers) {
        super(identifier, arrivalTime, (int) Math.ceil(((double)cruiseLength/40)), (int) Math.ceil(((double)numPassengers/50)));
    }
    
}