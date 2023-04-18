package pawtropolis.model.dto.map;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.model.dto.entity.EntityDTO;
import pawtropolis.model.dto.items.InventoryDTO;
import pawtropolis.model.dto.items.ItemStoredDTO;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.utility.RoomType;

import java.util.EnumMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RoomDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private RoomType type;
    @OneToOne
    @JoinColumn(name = "id")
    @MapKeyEnumerated(EnumType.STRING)
    private InventoryDTO inventoryDTO;
    @OneToMany
    private List<EntityDTO> npcs;
    @ManyToMany(mappedBy = "adiacentRooms")
    @JoinTable(name="room_connection",
            joinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="to_room_id", referencedColumnName="ID")
    )
    @MapKeyEnumerated(EnumType.STRING)
    private EnumMap<DirectionEnum, RoomDTO> adiacentRooms;
    private int chainPosition;
    private int positionX;
    private int positionY;

    public RoomDTO(String name, InventoryDTO inventoryDTO, List<EntityDTO> npcs, int positionX, int positionY, RoomType roomType, int chainPosition) {
        this.name = name;
        this.inventoryDTO = inventoryDTO;
        this.npcs = npcs;
        this.positionX=positionX;
        this.positionY=positionY;
        this.type=roomType;
        this.chainPosition=chainPosition;
        this.adiacentRooms = new EnumMap(DirectionEnum.class);
    }
}
