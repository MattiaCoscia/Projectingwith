package pawtropolis.persistence.dto.map;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@Table(name = "room")
public class RoomDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_id")
    private RoomType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private InventoryDTO inventoryDTO;

    @OneToMany
    private List<EntityDTO> npcs;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="door_connection",
            joinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="to_door_id", referencedColumnName="id")
    )
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyColumn(name = "direction_id",table = "door_connection")
    private EnumMap<DirectionEnum, DoorDTO> adiacentDoors;

    @Column(name = "chain_position")
    private int chainPosition;

    @Column(name = "position_x")
    private int positionX;

    @Column(name = "position_y")
    private int positionY;

    public RoomDTO() {
        this.adiacentDoors = new EnumMap<>(DirectionEnum.class);
        this.npcs = new ArrayList<>();
    }

    public void setAdiacentDoors(EnumMap<DirectionEnum, DoorDTO> adiacentDoors) {
        this.adiacentDoors = adiacentDoors;
    }
}
