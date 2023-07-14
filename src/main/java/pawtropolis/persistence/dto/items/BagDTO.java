package pawtropolis.persistence.dto.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.persistence.dto.DTOClass;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bag")
public class BagDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "inventory_id" ,referencedColumnName = "id")
    private InventoryDTO inventory;

    @Column(name = "volume")
    private int volume = 20;

    @Column(name = "occupied_volume")
    private int occupiedSlots=0;

    public BagDTO(InventoryDTO inventory){
        this.inventory = inventory;
    }
}
