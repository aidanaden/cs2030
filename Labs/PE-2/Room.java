import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class Room implements Tickable {

    private final Optional<Room> previousRoom;
    private final String roomName;
    private final ArrayList<Tickable> roomObjects;

    Room(String roomName) {

        this.roomName = roomName;
        this.roomObjects = new ArrayList<Tickable>();
        this.previousRoom = Optional.ofNullable(this);
    }

    Room(String roomName, ArrayList<Tickable> roomObjects) {
     
        this.roomName = roomName;
        this.roomObjects = roomObjects;
        this.previousRoom = Optional.ofNullable(this);
    }

    Room(String roomName, ArrayList<Tickable> roomObjects, Optional<Room> previousRoom) {

        this.roomName = roomName;
        this.roomObjects = roomObjects;
        this.previousRoom = previousRoom;
    }

    public String getName() {
        return this.roomName;
    }

    public ArrayList<Tickable> getRoomObjects() {
        return this.roomObjects;
    }

    public String toString() {

        String formatStr = String.format("@%s", this.roomName);
       
        for (Tickable t : this.roomObjects) {

            formatStr += "\n" + t.toString();
        }

        return formatStr;
    }

    public Room add(Tickable obj) {

        ArrayList<Tickable> newRoomObjects = new ArrayList<Tickable>();

        newRoomObjects.addAll(this.roomObjects);
        newRoomObjects.add(obj);

        return new Room(roomName, newRoomObjects);
    }

    public Room tick() {

        ArrayList<Tickable> newRoomObjects = new ArrayList<Tickable>();

        for (Tickable t : this.roomObjects) {

            Tickable newT = t.tick();
            newRoomObjects.add(newT);
        }

        return new Room(this.roomName, newRoomObjects, this.previousRoom);
    }

    public Room tick(Function<ArrayList<Tickable>, ArrayList<Tickable>> mapper) {
     
        ArrayList<Tickable> newRoomObjects = mapper.apply(this.roomObjects);   
        ArrayList<Tickable> updatedNewRoomObjects = new ArrayList<Tickable>();

        for (Tickable t : newRoomObjects) {

            Tickable newT = t.tick();
            updatedNewRoomObjects.add(newT);
        }

        return new Room(this.roomName, updatedNewRoomObjects, this.previousRoom);
    }

    public Room go(Function<ArrayList<Tickable>, Room> mapper) {

        Room newRoom = mapper.apply(this.roomObjects);
        ArrayList<Tickable> combinedRoomObjects = new ArrayList<Tickable>();
        
        if (getSwordIndex().isPresent()) {

            Tickable t = this.roomObjects.get(getSwordIndex().get());
            Sword s = (Sword) t;

            if (s.isTaken()) {
                combinedRoomObjects.add(t);
                this.roomObjects.remove(t);
            }
        }

        combinedRoomObjects.addAll(newRoom.getRoomObjects());
        return new Room(newRoom.getName(), combinedRoomObjects, Optional.ofNullable(this));
    }

    public Room back() {

        Function<ArrayList<Tickable>, ArrayList<Tickable>> f = x -> {

            ArrayList<Tickable> newX = new ArrayList<Tickable>(x);

            if (getSwordIndex().isPresent()) {

                Tickable t = this.roomObjects.get(getSwordIndex().get());
                Sword s = (Sword) t;

                if (s.isTaken()) {

                    newX.add(t);
                }    
            }

            return newX;
        };

        return this.previousRoom.get().tick(f);
    }

    public Optional<Integer> getSwordIndex() {

        for (Tickable t : this.roomObjects) {

            if (t instanceof Sword) {

                return Optional.of(this.roomObjects.indexOf(t));
            }
        }

        return Optional.empty();
    }
}

















