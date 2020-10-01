import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Contact {
    
    private final List<Person> people;
    private final double time;

    public Contact(Person first, Person second, double time) {

        this.people = new ArrayList<Person>();
        this.people.add(first);
        this.people.add(second);
        this.time = time;
    }

    public List<Person> getPeople() {
        return this.people;
    }

    public double timeOfContact() {
        return this.time;
    }

    public List<Person> transmit(double random) {

        Person firstPerson = this.people.get(0);
        Person secondPerson = this.people.get(1);

        List<Virus> firstResultingVirus = firstPerson.transmit(random);
        List<Virus> secondResultingVirus = secondPerson.transmit(random);

        Person newFirstPerson = firstPerson.infectWith(secondResultingVirus, random);
        Person newSecondPerson = secondPerson.infectWith(firstResultingVirus, random);

        List<Person> infectedPeople = new ArrayList<Person>();
        infectedPeople.add(newFirstPerson);
        infectedPeople.add(newSecondPerson);

        return infectedPeople;
    }

    
}
