package pawtropolis.persistence.dto.map;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.Door;
import pawtropolis.persistence.dto.DTOClass;
import pawtropolis.persistence.dto.entity.EntityDTO;
import pawtropolis.persistence.dto.items.InventoryDTO;
import pawtropolis.utility.RoomType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "room")
public class RoomDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @OneToOne
    @JoinColumn(name = "id")
    private InventoryDTO inventoryDTO;

    @OneToMany
    private List<EntityDTO> npcs;

    @ManyToMany
    @JoinTable(name="room_connection",
            joinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="to_room_id", referencedColumnName="id")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "direction_id",table = "room_connection")
    private EnumMap<DirectionEnum, RoomDTO> adiacentRooms;

    @ManyToMany
    @JoinTable(name="door_connection",
            joinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="to_room_id", referencedColumnName="id")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "direction_id",table = "door_connection")
    private EnumMap<DirectionEnum, DoorDTO> adiacentDoors;

    private int chainPosition;

    private int positionX;

    private int positionY;

    public RoomDTO() {
        this.adiacentRooms = new EnumMap(DirectionEnum.class);
        this.npcs = new ArrayList<>();
    }
    public RoomDTO setSingleRoom(DirectionEnum directionEnum, RoomDTO room){
        DoorDTO door = new DoorDTO();
        this.adiacentDoors.put(directionEnum,door);
        this.adiacentRooms.put(directionEnum,room);

        DirectionEnum opposite = DirectionEnum.oppositeValue(directionEnum);
        room.adiacentRooms.put(opposite,this);
        room.adiacentDoors.put(opposite,door);
        return room;
    }

    public RoomDTO setSingleRoom(DirectionEnum directionEnum,DoorDTO door, RoomDTO room){
        this.adiacentDoors.put(directionEnum,door);
        this.adiacentRooms.put(directionEnum,room);

        DirectionEnum opposite = DirectionEnum.oppositeValue(directionEnum);
        room.adiacentRooms.put(opposite,this);
        room.adiacentDoors.put(opposite,door);
        return room;
    }
}
