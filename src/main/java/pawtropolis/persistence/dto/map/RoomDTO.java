package pawtropolis.persistence.dto.map;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.persistence.dto.DTOClass;
import pawtropolis.persistence.dto.entity.EntityDTO;
import pawtropolis.persistence.dto.items.InventoryDTO;
import pawtropolis.utility.RoomType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Getter
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<EntityDTO> npcs;

    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyColumn(name = "direction_id", table = "door_connection")
    private Map<DirectionEnum, DoorDTO> adiacentDoors;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setInventoryDTO(InventoryDTO inventoryDTO) {
        this.inventoryDTO = inventoryDTO;
    }

    public void setNpcs(List<EntityDTO> npcs) {
        this.npcs = npcs;
    }

    public void setChainPosition(int chainPosition) {
        this.chainPosition = chainPosition;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
