package pawtropolis.persistence.dto.map;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.persistence.dto.DTOClass;
import pawtropolis.persistence.dto.items.InventoryDTO;
import pawtropolis.persistence.dto.items.ItemStoredDTO;

import java.util.EnumMap;
import java.util.Map;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "door")
public class DoorDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name="door_connection",
            joinColumns=
            @JoinColumn(name="to_door_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="id")
    )
    @MapKeyEnumerated(value = EnumType.STRING)
    @MapKeyColumn(name = "direction_id",table = "door_connection")
    private Map<DirectionEnum,RoomDTO> rooms;

    @Column(name = "is_open")
    private boolean isOpen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "inventory_id")
    private InventoryDTO inventoryDTO;

    public DoorDTO() {
        this.rooms = new EnumMap<>(DirectionEnum.class);
        this.inventoryDTO = new InventoryDTO();
    }
}
