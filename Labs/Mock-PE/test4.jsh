/open SimulationParameters.java
/open Virus.java
/open BetaCoronavirus.java
/open SARS_CoV_2.java
/open AlphaCoronavirus.java
/open Person.java
/open Contact.java
/open Location.java
Person mingsoon = new Person("Ming Soon")
Person longThePerson = new Person("Long")
Location l = new Location("LT19")
l
Arrays.toString(l.getOccupants().toArray())
Arrays.toString(l.accept(mingsoon)
        .getOccupants().toArray())
Arrays.toString(l.accept(mingsoon)
        .accept(longThePerson)
        .getOccupants().toArray())
Arrays.toString(l.accept(mingsoon)
        .accept(longThePerson)
        .remove(mingsoon.toString())
        .getOccupants().toArray())
Arrays.toString(l.accept(mingsoon).accept(longThePerson).remove(mingsoon.toString()).remove(longThePerson.toString()).getOccupants().toArray())
/exit
