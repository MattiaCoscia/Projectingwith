package pawtropolis.persistence.dto.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.items.ItemStored;
import pawtropolis.persistence.dto.DTOClass;
import pawtropolis.persistence.dto.items.ItemStoredDTO;

@Getter
@Setter
@Entity
@Table(name = "door")
public class DoorDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "is_open")
    private boolean isOpen;

    @OneToOne
    @JoinColumn(referencedColumnName = "id",name = "itemkey_id")
    private ItemStoredDTO keyItem;
}
