import java.util.List;

public class DormsWithShn extends Dorms {
    
    private static final String LOG_STRING = "%%s %s %%.3f";
    private static final String TESTS_POSITIVE = "tests positive for %s " +
        "at time";
    private static final String TESTS_NEGATIVE = "test negative for %s " +
        "at time";

    DormsWithShn(boolean verbose) {
        super(verbose);
    }

    /**
     * Handles the sick person.
     * @param person The latest state of the sick Person
     * @param time   The time this is handled
     */
    @Override
    void handleSickPerson(Person person, double time) {
        if (!person.test(SimulationParameters.TARGET_VIRUS)) {
            logNegativeTest(person, time);
        } else {

            Person updatedPerson = new Person(person.toString(), person.getListOfViruses(), time, time + 28.0);
            super.updatePerson(updatedPerson);
            logPositiveTest(person, time);

            List<? extends Contact> shnContacts = super.queryContacts(updatedPerson.toString(), time);

            for (Contact shnContact: shnContacts) {

                for (Person shnContactPerson: shnContact.getPeople()) {

                    if (shnContactPerson.toString() == person.toString()) {
                        Person updatedShnContactPerson = new Person(shnContactPerson.toString(), shnContactPerson.getListOfViruses(), time, time + SimulationParameters.SHN_DURATION);
                        super.updatePerson(updatedShnContactPerson);
                    }
                }
            }
        }
    }

    private final String buildLog(String s) {
        return String.format(LOG_STRING, s);
    }

    private final void logNegativeTest(Person person, double time) {
        log(String.format(buildLog(TESTS_NEGATIVE), 
                    person, SimulationParameters.TARGET_VIRUS, time));
    }

    private final void logPositiveTest(Person person, double time) {
        log(String.format(buildLog(TESTS_POSITIVE),
                    person, SimulationParameters.TARGET_VIRUS, time));
    }

}
