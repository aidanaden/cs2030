import java.util.ArrayList;
import java.util.function.Function;

public class Room implements Tickable {

    private final Room previousRoom;
    private final String roomName;
    private final ArrayList<Tickable> roomObjects;

    Room(String roomName) {

        this.roomName = roomName;
        this.roomObjects = new ArrayList<Tickable>();
        this.previousRoom = null;
    }

    Room(String roomName, ArrayList<Tickable> roomObjects) {
     
        this.roomName = roomName;
        this.roomObjects = roomObjects;
        this.previousRoom = null;
    }

    Room(String roomName, ArrayList<Tickable> roomObjects, Room previousRoom) {

        this.roomName = roomName;
        this.previousRoom = previousRoom;
        this.roomObjects = roomObjects;
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

        return new Room(this.roomName, newRoomObjects);
    }

    public Room tick(Function<ArrayList<Tickable>, ArrayList<Tickable>> mapper) {
     

        ArrayList<Tickable> newRoomObjects = mapper.apply(this.roomObjects);
        
        ArrayList<Tickable> updatedNewRoomObjects = new ArrayList<Tickable>();

        for (Tickable t : newRoomObjects) {

            Tickable newT = t.tick();
            updatedNewRoomObjects.add(newT);
        }

        return new Room(this.roomName, updatedNewRoomObjects);
    }

    public Room go(Function<ArrayList<Tickable>, Room> mapper) {

        Room newRoom = mapper.apply(this.roomObjects);
        
        if (newRoom.getRoomObjects().containsAll(this.roomObjects)) {

            return newRoom;
        
        } else {

            ArrayList<Tickable> combinedRoomObjects = new ArrayList<Tickable>();
            combinedRoomObjects.addAll(this.roomObjects);
            combinedRoomObjects.addAll(newRoom.getRoomObjects());

            Room combinedNewRoom = new Room(newRoom.getName(), combinedRoomObjects);

            return combinedNewRoom;
        }
    }

    public Room back() {

        return this.previousRoom.tick();
    }
}

















