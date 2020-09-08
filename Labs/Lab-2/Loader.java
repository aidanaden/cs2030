class Loader {
    private final int identifier;
    private final Cruise cruise;


    Loader(int identifier, Cruise cruise) {
        this.identifier = identifier;
        this.cruise = cruise;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public String toString() {
        String returnString = String.format("Loader %d serving ", this.identifier);
        return (returnString + this.cruise);
    }

    public Loader serve(Cruise cruiseToServe) {
        if (canServe(cruiseToServe)) {
     
            Loader loader = new Loader(this.identifier, cruiseToServe);
            return loader;

        } else {

            return this;
        }
    }

    public boolean canServe(Cruise cruiseToServe) {
        if (this.getNextAvailableTime() <= cruiseToServe.getArrivalTime()) {
            return true;
        } else {
            return false;
        }
    }

    public int getNextAvailableTime() {

        return this.cruise.getServiceCompletionTime();
    }
}
