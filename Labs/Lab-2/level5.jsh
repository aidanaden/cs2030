/open Cruise.java
/open SmallCruise.java
/open BigCruise.java 
/open Loader.java 


import java.util.ArrayList;


public void serveCruises(Cruise[] cruises) {

    ArrayList<Loader> inventory = new ArrayList<Loader> ();
    ArrayList<Loader> sequence = new ArrayList<Loader> ();

    for (Cruise currentCruise: cruises) {

        ArrayList<Loader> availables = new ArrayList<Loader> ();

        for (Loader inventoryLoader: inventory) {

            if (inventoryLoader.canServe(currentCruise)) {

                Loader updatedInventoryLoader = inventoryLoader.serve(currentCruise);

                availables.add(availables.size(), updatedInventoryLoader);
            }
        }

        if (availables.size() <= currentCruise.getNumOfLoadersRequired()) {

            for (Loader availableLoader: availables) {

                sequence.add(sequence.size(), availableLoader);   
                inventory.set(availableLoader.getIdentifier() - 1, availableLoader);             
            }

            while (availables.size() < currentCruise.getNumOfLoadersRequired()) {

                int identifier = inventory.size() + 1;
                int index = inventory.size();
                Loader newInventoryLoader = new Loader(identifier, currentCruise);

                inventory.add(index, newInventoryLoader);
                availables.add(availables.size(), newInventoryLoader);
                sequence.add(sequence.size(), newInventoryLoader);
            }
        }  
        
        else if (availables.size() > currentCruise.getNumOfLoadersRequired()) {

            while (availables.size() > currentCruise.getNumOfLoadersRequired()) {

                int lastIndex = availables.size() - 1;
                availables.remove(lastIndex);
            }

            for (Loader availableLoader : availables) {
                sequence.add(sequence.size(), availableLoader);
                inventory.set(availableLoader.getIdentifier() - 1, availableLoader);
            }
        }
    }

    for (Loader sequenceLoader: sequence) {
        System.out.println(sequenceLoader);
    }
}