package pawtropolis.model.map;

public class GameMap {
    private Room[][] rooms;

    public GameMap(Room[][] rooms) {
        this.rooms = rooms;
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    public void setRoom(Room room){
        this.rooms[room.getPositionX()][room.getPositionY()]=room;
    }
}
