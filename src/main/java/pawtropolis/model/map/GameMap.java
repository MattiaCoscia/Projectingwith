package pawtropolis.model.map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.BusinessClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Getter
@Setter
@Component
public class GameMap extends BusinessClass {
    private int id;
    private int widthMap;
    private int heightMap;
    private Map<String,Room> rooms;
    private String name;
    @Autowired
    public GameMap() {
        this.rooms = new HashMap<>();
        widthMap = 10;
        heightMap = 10;
        name = new Random().nextInt(0,262323)+" ";
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
