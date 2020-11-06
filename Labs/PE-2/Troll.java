public class Troll implements Tickable {


    private final int firstState = 0;
    private final int secondState = 1;
    private final int thirdState = 2;
    private final int fourthState = 3;

    private final int state;

    Troll() {
        this.state = 0;
    }

    Troll(int state) {
        this.state = state;
    }

    public String toString() {

        if (this.state == firstState) {
            return "Troll lurks in the shadows.";
        } else if (this.state == secondState) {
            return "Troll is getting hungry.";
        } else if (this.state == thirdState) {
            return "Troll is VERY hungry.";
        } else if (this.state == fourthState) {
            return "Troll is SUPER HUNGRY and is about to ATTACK!";
        } else {
            return "Troll attacks!";
        }
    }


    public Troll tick() {
        return new Troll(this.state + 1);
    }
}
