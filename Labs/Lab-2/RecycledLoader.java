public class RecycledLoader extends Loader {
    
    RecycledLoader(int identifier, Cruise cruise) {
        super(identifier, cruise);
    }

    @Override
    public int getNextAvailableTime() {

        return super.getNextAvailableTime() + 60;
    }

    @Override
    public String toString() {
        return "Recycled " + super.toString();
    }

    @Override
    public RecycledLoader serve(Cruise cruiseToServe) {
        if (super.canServe(cruiseToServe)) {
     
            RecycledLoader recycledLoader = new RecycledLoader(super.getIdentifier(), cruiseToServe);
            return recycledLoader;

        } else {

            return this;
        }
    }
}
