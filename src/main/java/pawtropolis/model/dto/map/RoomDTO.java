package pawtropolis.model.dto.map;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.dto.DTOClass;
import pawtropolis.model.dto.items.InventoryDTO;
import pawtropolis.model.map.DirectionEnum;
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
    private List<pawtropolis.model.dto.entity.EntityDTO> npcs;

    @ManyToMany(mappedBy = "adiacentRooms")
    @JoinTable(name="room_connection",
            joinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="to_room_id", referencedColumnName="ID")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "direction_id",table = "room_connection")
    private EnumMap<DirectionEnum, RoomDTO> adiacentRooms;

    private int chainPosition;

    private int positionX;

    private int positionY;

    public RoomDTO() {
        this.adiacentRooms = new EnumMap(DirectionEnum.class);
        this.npcs = new ArrayList<>();
    }
    public RoomDTO setSingleRoom(DirectionEnum directionEnum, RoomDTO room){
        this.adiacentRooms.put(directionEnum,room);
        return room;
    }
    public RoomDTO setSingleRoom(int pos,RoomDTO room){
        this.adiacentRooms.put(DirectionEnum.values()[pos],room);
        return room;
    }
}
