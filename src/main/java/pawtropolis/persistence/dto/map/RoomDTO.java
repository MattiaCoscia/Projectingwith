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
    @JoinTable(name="door_connection",
            joinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="to_door_id", referencedColumnName="id")
    )
    @MapKeyColumn(name = "direction_id")
    @MapKeyEnumerated
    private EnumMap<DirectionEnum, DoorDTO> adiacentDoors;

    private int chainPosition;

    private int positionX;

    private int positionY;

    public RoomDTO() {
        this.adiacentDoors = new EnumMap(DirectionEnum.class);
        this.npcs = new ArrayList<>();
    }
}
