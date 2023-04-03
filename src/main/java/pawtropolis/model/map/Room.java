package pawtropolis.model.map;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;
import pawtropolis.utility.RoomType;

import java.util.EnumMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@jakarta.persistence.Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private RoomType type;
    @OneToOne
    @JoinColumn(name = "id")
    @MapKeyEnumerated(EnumType.STRING)
    private Inventory inventory;
    @OneToMany
    private List<Entity> npcs;
    @ManyToMany(mappedBy = "adiacentRooms")
    @JoinTable(name="room_connection",
            joinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="to_room_id", referencedColumnName="ID")
    )
    @MapKeyEnumerated(EnumType.STRING)
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
    public Room setSingleRoom(int pos,Room room){
        this.adiacentRooms.put(DirectionEnum.values()[pos],room);
        return room;
    }
    public List<Entity> getEntities() {
        return npcs;
    }

    public void addItem(ItemStored itemStored){
        inventory.addItemFromInventory(itemStored);
    }
    public ItemStored getItem(String item){
        return inventory.getItemFromInventory(item);
    }
}
