class Cruise {
    
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoader;
    private final int serviceTime;

    Cruise(String identifier, int arrivalTime, int numOfLoader, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoader = numOfLoader;
        this.serviceTime = serviceTime;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getNumOfLoadersRequired() {
        return this.numOfLoader;
    }

    public String toString() {
        return this.identifier + String.format("@%04d", this.arrivalTime);
    }

    public int getServiceCompletionTime() {
        int arrivalTimeMinutes = getArrivalTime();
        int serviceTimeMinutes = arrivalTimeMinutes + this.serviceTime;

        return serviceTimeMinutes;
    }

    public int getArrivalTime() {

        int hoursToMinutes = ((int) (arrivalTime / 100)) * 60;
        int minutesToMinutes = (arrivalTime % 100);
        int totalMinutes = hoursToMinutes + minutesToMinutes;

        return totalMinutes;
    }
}
