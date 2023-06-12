package pawtropolis.persistence.dto.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.persistence.dto.DTOClass;
import pawtropolis.persistence.dto.items.ItemStoredDTO;

import java.util.EnumMap;

@Getter
@Setter
@Entity
@Table(name = "door")
public class DoorDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    @JoinTable(name="door_connection",
            joinColumns=
            @JoinColumn(name="to_door_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="from_room_id", referencedColumnName="id")
    )
    @MapKeyColumn(name = "direction_id")
    @MapKeyEnumerated
    private EnumMap<DirectionEnum,RoomDTO> rooms;

    @Column(name = "is_open")
    private boolean isOpen;

    @OneToOne
    @JoinColumn(referencedColumnName = "id",name = "itemkey_id")
    private ItemStoredDTO keyItem;

    public DoorDTO() {
        this.rooms = new EnumMap(DirectionEnum.class);
    }
}
