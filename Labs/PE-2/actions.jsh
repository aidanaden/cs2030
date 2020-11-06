Function<ArrayList<Tickable>, ArrayList<Tickable>> takeSword = x -> {

    for (int i = 0; i < x.size(); i++) {

        Tickable t = x.get(i);

        if (t instanceof Sword) {

            Sword s = (Sword) t;

            if (!s.isTaken()) {

                Sword takenSword = new Sword(true);
                x.set(i, takenSword);

                System.out.println("--> You have taken sword.");

            }
            
            if (s.isTaken()) {

                System.out.println("--> You already have sword.");
            }

            break;
        }
    }

    return x;
}

Function<ArrayList<Tickable>, ArrayList<Tickable>> killTroll = x -> {

    for (int i = 0; i < x.size(); i++) {

        Tickable t1 = x.get(i);

        if (t1 instanceof Troll) {

            for (int j = 0; j < x.size(); j++) {

                Tickable t2 = x.get(j);

                if (t2 instanceof Sword) {

                    Sword s = (Sword) t2;

                    if (s.isTaken()) {

                        x.remove(i);
                        System.out.println("--> Troll is killed.");
                    
                    } else {

                        System.out.println("--> You have no sword.");
                    }

                    return x;
                }
            }

            System.out.println("You have no sword."); 
            return x;           
        }
    }

    System.out.println("--> There is no troll");
    return x;
}

















