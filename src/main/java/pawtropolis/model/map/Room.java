package pawtropolis.model.map;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.model.entity.Entity;
import pawtropolis.utility.RoomType;
import pawtropolis.model.items.Item;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Room {
    private String name;

    private RoomType type;
    private Map<String, Item> items;
    private List<Entity> npcs;
    private Room[] adiacentRooms=new Room[4];
    private int chainPosition;
    private int positionX;
    private int positionY;

    public Room(String name, Map<String, Item> items, List<Entity> npcs, int positionX, int positionY, RoomType roomType, int chainPosition) {
        this.name = name;
        this.items = items;
        this.npcs = npcs;
        this.positionX=positionX;
        this.positionY=positionY;
        this.type=roomType;
        this.chainPosition=chainPosition;
    }
    public Room setSingleRoom(int pos,Room room){
        this.adiacentRooms[pos]=room;
        return room;
    }
    public List<Entity> getEntities() {
        return npcs;
    }
}
