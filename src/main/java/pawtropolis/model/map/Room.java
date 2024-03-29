package pawtropolis.model.map;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.model.BusinessClass;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;
import pawtropolis.utility.RoomType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Room extends BusinessClass {
    private int id;
    private String name;
    private RoomType type;
    private Inventory inventory;
    private List<pawtropolis.model.entity.Entity> npcs;
    private EnumMap<DirectionEnum,Door> adiacentDoors;
    private int chainPosition;
    private int positionX;
    private int positionY;
    public Room(String name, Inventory inventory, List<pawtropolis.model.entity.Entity> npcs, int positionX, int positionY, RoomType roomType, int chainPosition) {
        this.name = name;
        this.inventory = inventory;
        this.npcs = npcs;
        this.positionX=positionX;
        this.positionY=positionY;
        this.type=roomType;
        this.chainPosition=chainPosition;
        this.adiacentDoors = new EnumMap(DirectionEnum.class);
    }
    public Room setSingleRoom(DirectionEnum directionEnum,Room room){
        Door door = new Door();
        door.getRooms().put(DirectionEnum.oppositeValue(directionEnum),this);
        door.getRooms().put(directionEnum,room);

        this.adiacentDoors.put(directionEnum,door);
        room.adiacentDoors.put(DirectionEnum.oppositeValue(directionEnum),door);
        return room;
    }
    public List<pawtropolis.model.entity.Entity> getEntities() {
        return npcs;
    }
    public void addItem(ItemStored itemStored){
        inventory.addItemTOInventory(itemStored);
    }
    public ItemStored getItem(String item){
        return inventory.getItemFromInventory(item);
    }
    public void removeItem(ItemStored item){this.inventory.removeItem(item);}
}
