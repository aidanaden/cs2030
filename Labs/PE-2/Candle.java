public class Candle implements Tickable {

    private final int firstState = 0;
    private final int secondState = 1;
    private final int thirdState = 2;
    
    private final int state;

    Candle() {
        
        this.state = 0;
    }

    Candle(int state) {

        this.state = state;
    }

    public Tickable tick() {

        return new Candle(this.state + 1);
    }

    public String toString() {

        if (this.state == firstState) {
            return "Candle flickers.";
        } else if (this.state == secondState) {
            return "Candle is getting shorter.";
        } else if (this.state == thirdState) {
            return "Candle is about to burn out.";
        } else {
            return "Candle has burned out.";
        }
    }


}
