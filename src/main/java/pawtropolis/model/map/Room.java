package pawtropolis.model.map;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.model.BusinessClass;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;
import pawtropolis.utility.RoomType;

import java.util.EnumMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Room implements BusinessClass {
    private int id;
    private String name;
    private RoomType type;
    private Inventory inventory;
    private List<Entity> npcs;
    private EnumMap<DirectionEnum,Room> adiacentRooms;
    private int chainPosition;
    private int positionX;
    private int positionY;
    public Room(String name, Inventory inventory, List<Entity> npcs, int positionX, int positionY, RoomType roomType, int chainPosition) {
        this.name = name;
        this.inventory = inventory;
        this.npcs = npcs;
        this.positionX=positionX;
        this.positionY=positionY;
        this.type=roomType;
        this.chainPosition=chainPosition;
        this.adiacentRooms = new EnumMap(DirectionEnum.class);
    }
    public Room setSingleRoom(DirectionEnum directionEnum,Room room){
        this.adiacentRooms.put(directionEnum,room);
        room.adiacentRooms.put(DirectionEnum.oppositeValue(directionEnum),this);
        return room;
    }
    public List<Entity> getEntities() {
        return npcs;
    }
    public void addItem(ItemStored itemStored){
        inventory.addItemTOInventory(itemStored);
    }
    public ItemStored getItem(String item){
        return inventory.getItemFromInventory(item);
    }
}
