public abstract class Virus {
    
    private final String name;
    private final double probabilityOfMutating;

    Virus(String name, double probabilityOfMutating) {
        this.name = name;
        this.probabilityOfMutating = probabilityOfMutating;
    }

    public double getProbabilityOfMutating() {
        return this.probabilityOfMutating;
    }

    public String getName() {
        return this.name;
    }

    public abstract Virus spread(double random);

    @Override 
    public String toString() {
        return this.name + String.format(" with %.3f probability of mutating", this.probabilityOfMutating);
    }
}
