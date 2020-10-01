import java.util.ArrayList;
import java.util.List;

public class MaskedPerson extends Person {
    
    public MaskedPerson(String name) {
        super(name);
    }

    public MaskedPerson(Person person) {
        super(person.toString(), person.getListOfViruses());
    }

    @Override
    public List<Virus> transmit(double random) {

        List<Virus> resultingViruses = new ArrayList<Virus>();

        for (Virus virus: super.getListOfViruses()) {
            
            if (virus.getName() == "Alpha Coronavirus") {
                
                AlphaCoronavirus alphaCoronavirus = (AlphaCoronavirus) virus;

                if (random > SimulationParameters.MASK_EFFECTIVENESS) {
            
                    resultingViruses.add(alphaCoronavirus.spread(random));
                }

            } else if (virus.getName() == "SARS-CoV-2") {

                SARS_CoV_2 sarsCov2 = (SARS_CoV_2) virus;

                if (random > SimulationParameters.MASK_EFFECTIVENESS) {
            
                    resultingViruses.add(sarsCov2.spread(random));
                }

            } else {

                BetaCoronavirus betaCoronavirus = (BetaCoronavirus) virus;

                if (random > SimulationParameters.MASK_EFFECTIVENESS) {
            
                    resultingViruses.add(betaCoronavirus.spread(random));
                }
            }

        }

        return resultingViruses;
    }

    @Override
    public MaskedPerson infectWith(List<Virus> listOfViruses, double random) {

        if (random > SimulationParameters.MASK_EFFECTIVENESS) {

            Person infectedPerson = super.infectWith(listOfViruses, random);
            MaskedPerson infectMaskedPerson = new MaskedPerson(infectedPerson);

            return infectMaskedPerson; 

        } else {
            return this;
        }
    }
}
