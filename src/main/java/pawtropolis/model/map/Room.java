package pawtropolis.model.map;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.items.ItemStored;
import pawtropolis.utility.RoomType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Room {
    private String name;

    private RoomType type;
    private Map<String, ItemStored> items;
    private List<Entity> npcs;
    private EnumMap<DirectionEnum,Room> adiacentRooms;
    private int chainPosition;
    private int positionX;
    private int positionY;

    public Room(String name, Map<String, ItemStored> items, List<Entity> npcs, int positionX, int positionY, RoomType roomType, int chainPosition) {
        this.name = name;
        this.items = items;
        this.npcs = npcs;
        this.positionX=positionX;
        this.positionY=positionY;
        this.type=roomType;
        this.chainPosition=chainPosition;
        this.adiacentRooms = new EnumMap<DirectionEnum, Room>(DirectionEnum.class);
    }
    public Room setSingleRoom(int pos,Room room){
        this.adiacentRooms.put(DirectionEnum.values()[pos],room);
        return room;
    }
    public List<Entity> getEntities() {
        return npcs;
    }
}
