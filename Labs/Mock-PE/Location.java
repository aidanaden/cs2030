import java.util.ArrayList;
import java.util.List;

public class Location {
    
    private final String name;
    private final List<Person> occupants;

    public Location(String name) {
        this.name = name;
        this.occupants = new ArrayList<Person>();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void addOccupants(List<Person> occupants) {
        this.occupants.addAll(occupants);
    }

    public List<Person> getOccupants() {
        return this.occupants;
    }

    public Location accept(Person person) {

        Location newLocation = new Location(this.name);

        List<Person> newOccupants = new ArrayList<Person>();
        newOccupants.addAll(this.occupants);
        newOccupants.add(person);

        newLocation.addOccupants(newOccupants);

        return newLocation;
    }

    public Location remove(String personName) {
        
        Location newLocation = new Location(this.name);
        List<Person> newOccupants = new ArrayList<Person>();

        for (Person person: this.occupants) {
            if (person.toString() != personName) {
                newOccupants.add(person);
            }
        }

        newLocation.addOccupants(newOccupants);

        return newLocation;
    }
}
