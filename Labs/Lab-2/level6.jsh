/open Cruise.java
/open SmallCruise.java
/open BigCruise.java 
/open Loader.java 
/open RecycledLoader.java


import java.util.ArrayList;


public void serveCruises(Cruise[] cruises) {

    ArrayList<Loader> inventory = new ArrayList<Loader> ();
    ArrayList<Loader> sequence = new ArrayList<Loader> ();

    for (Cruise currentCruise: cruises) {

        ArrayList<Loader> availables = new ArrayList<Loader> ();

        for (int i=0; i<inventory.size(); i++) {

            Loader inventoryLoader = inventory.get(i);

            if ((inventoryLoader.getIdentifier() % 3 == 0) && (inventoryLoader.getIdentifier() > 0)) {

                RecycledLoader recycledInventoryLoader = (RecycledLoader) inventory.get(i);

                if (recycledInventoryLoader.canServe(currentCruise)) {
                    
                    RecycledLoader updatedRecycledInventoryLoader = recycledInventoryLoader.serve(currentCruise);
                    availables.add(availables.size(), updatedRecycledInventoryLoader);
                }
            }

            else {

                if (inventoryLoader.canServe(currentCruise)) {

                    Loader updatedInventoryLoader = inventoryLoader.serve(currentCruise);
                    availables.add(availables.size(), updatedInventoryLoader);
                }
            }

        }

        if (availables.size() <= currentCruise.getNumOfLoadersRequired()) {

            for (int i=0; i<availables.size(); i++) {

                Loader availableLoader = availables.get(i);

                if ((availableLoader.getIdentifier() % 3 == 0) && availableLoader.getIdentifier() > 0 ) {

                    RecycledLoader recycledAvailableLoader = (RecycledLoader) availables.get(i);
                    sequence.add(sequence.size(), recycledAvailableLoader);   
                    inventory.set(recycledAvailableLoader.getIdentifier() - 1, recycledAvailableLoader); 
                }

                else {

                    sequence.add(sequence.size(), availableLoader);   
                    inventory.set(availableLoader.getIdentifier() - 1, availableLoader); 
                }

            }

            while (availables.size() < currentCruise.getNumOfLoadersRequired()) {

                int identifier = inventory.size() + 1;
                int index = inventory.size();

                if ((identifier % 3 == 0) && (identifier > 0)){
                
                    RecycledLoader newRecycledInventoryLoader = new RecycledLoader(identifier, currentCruise);

                    inventory.add(index, newRecycledInventoryLoader);
                    availables.add(availables.size(), newRecycledInventoryLoader);
                    sequence.add(sequence.size(), newRecycledInventoryLoader);
                
                } else {
                    
                    Loader newInventoryLoader = new Loader(identifier, currentCruise);

                    inventory.add(index, newInventoryLoader);
                    availables.add(availables.size(), newInventoryLoader);
                    sequence.add(sequence.size(), newInventoryLoader);
                }
            }
        }  
        
        else if (availables.size() > currentCruise.getNumOfLoadersRequired()) {

            while (availables.size() > currentCruise.getNumOfLoadersRequired()) {

                int lastIndex = availables.size() - 1;
                availables.remove(lastIndex);
            }

            for (int i=0; i<availables.size(); i++) {

                Loader availableLoader = availables.get(i);

                if ((availableLoader.getIdentifier() % 3 == 0) && (availableLoader.getIdentifier() > 0 )) {

                    RecycledLoader recycledAvailableLoader = (RecycledLoader) availables.get(i);
                    sequence.add(sequence.size(), recycledAvailableLoader);
                    inventory.set(recycledAvailableLoader.getIdentifier() - 1, recycledAvailableLoader);

                } else {

                    sequence.add(sequence.size(), availableLoader);
                    inventory.set(availableLoader.getIdentifier() - 1, availableLoader);
                }
            }

        }
    }

    for (int i=0; i<sequence.size(); i++) {

        Loader sequenceLoader = sequence.get(i);

        if ((sequenceLoader.getIdentifier() % 3 == 0) && (sequenceLoader.getIdentifier() > 0)) {

            RecycledLoader recycledSequenceLoader = (RecycledLoader) sequence.get(i);
            System.out.println(recycledSequenceLoader);
        
        } else {

            System.out.println(sequenceLoader);
        }
    }
}