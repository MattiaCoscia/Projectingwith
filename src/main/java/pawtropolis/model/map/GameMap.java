package pawtropolis.model.map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class GameMap {
    private Room[][] rooms;
    @Autowired
    public GameMap() {
        this.rooms = new Room[10][10];
    }
    public void setSingleRoom(Room room){
        this.rooms[room.getPositionY()][room.getPositionX()]=room;
    }
}
