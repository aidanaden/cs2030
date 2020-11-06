public class Sword implements Tickable {

    private final boolean taken;

    Sword() {
        this.taken = false;
    }

    Sword(boolean taken) {
        this.taken = taken;
    }

    public boolean isTaken() {
        return this.taken;
    }

    public String toString() {
        return "Sword is shimmering.";
    }

    public Sword tick() {
        return new Sword(this.taken);
    }
}
