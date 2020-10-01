import java.util.ArrayList;
import java.util.List;

public class Person {
    
    private final String name;
    private final List<Virus> listOfViruses;
    private final double onSHNTimeStart;
    private final double onSHNTimeEnd;

    public Person(String name) {
        this.name = name;
        this.listOfViruses = new ArrayList<Virus>();
        this.onSHNTimeStart = 0.0;
        this.onSHNTimeEnd = 0.0;
    }

    public Person(String name, List<Virus> listOfViruses) {
        
        this.name = name;
        this.listOfViruses = listOfViruses;
        this.onSHNTimeStart = 0.0;
        this.onSHNTimeEnd = 0.0;
    }

    public Person(String name, List<Virus> listOfViruses, double onSHNStart, double onSHNEnd) {
        this.name = name;
        this.listOfViruses = listOfViruses;
        this.onSHNTimeStart = onSHNStart;
        this.onSHNTimeEnd = onSHNEnd;
    }

    public boolean onSHN(double currentTime) {

        if ((currentTime >= onSHNTimeStart) && (currentTime <= onSHNTimeEnd)) {
            return true;
        } else {
            return false;
        }
    }


    public List<Virus> getListOfViruses() {
        return this.listOfViruses;
    }

    @Override 
    public String toString() {
        return this.name;
    }

    public List<Virus> transmit(double random) {

        List<Virus> resultingViruses = new ArrayList<Virus>();

        for (Virus virus: this.listOfViruses) {
            
            if (virus.getName() == "Alpha Coronavirus") {
                
                AlphaCoronavirus alphaCoronavirus = (AlphaCoronavirus) virus;
                resultingViruses.add(alphaCoronavirus.spread(random));

            } else if (virus.getName() == "SARS-CoV-2") {

                SARS_CoV_2 sarsCov2 = (SARS_CoV_2) virus;
                resultingViruses.add(sarsCov2.spread(random));

            } else {

                BetaCoronavirus betaCoronavirus = (BetaCoronavirus) virus;
                resultingViruses.add(betaCoronavirus.spread(random));
            }
        }

        return resultingViruses;
    }

    public Person infectWith(List<Virus> listOfViruses, double random) {

        List<Virus> combineListOfViruses = new ArrayList<Virus>();
        combineListOfViruses.addAll(this.listOfViruses);
        combineListOfViruses.addAll(listOfViruses);
        
        Person infectedPerson = new Person(this.name, combineListOfViruses);
        return infectedPerson;
    }

    public boolean test(String name) {
        for (Virus virus: this.listOfViruses) {
            if (virus.getName() == name) {
                return true;
            }
        }
        return false;
    }
}
