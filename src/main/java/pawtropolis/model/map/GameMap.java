package pawtropolis.model.map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pawtropolis.model.BusinessClass;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
public class GameMap extends BusinessClass {
    private int id;
    private int widthMap;
    private int heightMap;
    private Map<String,Room> rooms;
    public GameMap() {
        this.rooms = new HashMap<>();
        widthMap = 10;
        heightMap = 10;
    }
    public void setSingleRoom(Room room){
        Room roomToSet = rooms.get(room.getName());
        if(roomToSet != null){
            rooms.remove(roomToSet);
            rooms.put(room.getName(),room);
        }else{
            rooms.put(room.getName(),room);
        }
    }
}
