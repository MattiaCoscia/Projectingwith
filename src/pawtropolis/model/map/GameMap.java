package pawtropolis.model.map;

import lombok.Getter;

@Getter
public class GameMap {
    private Room[][] rooms;

    public GameMap(Room[][] rooms) {
        this.rooms = rooms;
    }


    public void setRoom(Room room){
        this.rooms[room.getPositionY()][room.getPositionX()]=room;
    }
}
