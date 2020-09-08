import java.util.ArrayList;


public void serveCruises(Cruise[] cruises) {

    ArrayList<Loader> inventory = new ArrayList<Loader> ();
    ArrayList<Loader> sequence = new ArrayList<Loader> ();

    for (Cruise currentCruise: cruises) {

        ArrayList<Loader> availables = new ArrayList<Loader> ();

        // check inventory for available loaders
        for (int i=0; i<inventory.size(); i++) {

            Loader inventoryLoader = inventory.get(i);

            // if loader identifier is a multiple of 3, instantiate as a RecycledLoader (Part 6)
            if ((inventoryLoader.getIdentifier() % 3 == 0) && (inventoryLoader.getIdentifier() > 0)) {

                // type cast to RecycledLoader
                RecycledLoader recycledInventoryLoader = (RecycledLoader) inventory.get(i);

                // if RecycledLoader can serve current cruise, update it with current cruiser
                // and add it to list of available Loaders.
                if (recycledInventoryLoader.canServe(currentCruise)) {
                    
                    RecycledLoader updatedRecycledInventoryLoader = recycledInventoryLoader.serve(currentCruise);
                    availables.add(availables.size(), updatedRecycledInventoryLoader);
                }
            }

            else {

                // else if loader identifier is NOT a multiple of 3, leave it as a Loader
                // and check if it's available
                if (inventoryLoader.canServe(currentCruise)) {

                    // if Loader can serve current cruise, update it with current cruiser
                    // and add it to list of available Loaders.
                    Loader updatedInventoryLoader = inventoryLoader.serve(currentCruise);
                    availables.add(availables.size(), updatedInventoryLoader);
                }
            }

        }

        // if number of available loaders is less than (or equal) required,
        if (availables.size() <= currentCruise.getNumOfLoadersRequired()) {


            // add the existing available loaders to the sequence and 
            // update the loaders in the inventory
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

            // if not enough available loaders, create new
            // loaders for the current cruise and add it to
            // the inventory and the sequence
            while (availables.size() < currentCruise.getNumOfLoadersRequired()) {

                int identifier = inventory.size() + 1;
                int index = inventory.size();

                // if the identifier is a multiple of 3, set it as 
                // a RecycledLoader
                if ((identifier % 3 == 0) && (identifier > 0)){
                
                    RecycledLoader newRecycledInventoryLoader = new RecycledLoader(identifier, currentCruise);

                    // add to inventory and sequence 
                    // (not necessary to add to availables, just for future proof)
                    inventory.add(index, newRecycledInventoryLoader);
                    availables.add(availables.size(), newRecycledInventoryLoader);
                    sequence.add(sequence.size(), newRecycledInventoryLoader);
                
                } else {
                    
                // else if identifier is not a multiple of 3, set it 
                // as a normal Loader
                    Loader newInventoryLoader = new Loader(identifier, currentCruise);

                    // add to inventory and sequence 
                    // (not necessary to add to availables, just for future proof)
                    inventory.add(index, newInventoryLoader);
                    availables.add(availables.size(), newInventoryLoader);
                    sequence.add(sequence.size(), newInventoryLoader);
                }
            }
        }  
        
        // if there's too many available loaders
        else if (availables.size() > currentCruise.getNumOfLoadersRequired()) {

            // cycle through the available loaders and remove the 
            // excess loaders
            while (availables.size() > currentCruise.getNumOfLoadersRequired()) {

                int lastIndex = availables.size() - 1;
                availables.remove(lastIndex);
            }

            // update available inventory loaders with the current cruise
            // and add it to the sequence 
            for (int i=0; i<availables.size(); i++) {

                Loader availableLoader = availables.get(i);

                // if identifier is a multiple of 3, instantiate as a RecycledLoader
                if ((availableLoader.getIdentifier() % 3 == 0) && (availableLoader.getIdentifier() > 0 )) {

                    RecycledLoader recycledAvailableLoader = (RecycledLoader) availables.get(i);
                    sequence.add(sequence.size(), recycledAvailableLoader);
                    inventory.set(recycledAvailableLoader.getIdentifier() - 1, recycledAvailableLoader);

                } else {

                // otherwise, if identifier is not a multiple of 3, 
                // instantiate as a normal Loader
                    sequence.add(sequence.size(), availableLoader);
                    inventory.set(availableLoader.getIdentifier() - 1, availableLoader);
                }
            }

        }
    }

    // cycle through each loader in the sequence 
    // and print each loader out
    for (int i=0; i<sequence.size(); i++) {

        Loader sequenceLoader = sequence.get(i);

        // if identifier of Loader is a multiple of 3, instantiate as a RecycledLoader
        if ((sequenceLoader.getIdentifier() % 3 == 0) && (sequenceLoader.getIdentifier() > 0)) {

            RecycledLoader recycledSequenceLoader = (RecycledLoader) sequence.get(i);
            System.out.println(recycledSequenceLoader);
        
        } else {
        
        // otherwise, if identifier of Loader is not a multiple of 3, 
        // instantiate it as a Loader
            System.out.println(sequenceLoader);
        }
    }
}
