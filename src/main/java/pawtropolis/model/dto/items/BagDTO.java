package pawtropolis.model.dto.items;

import jakarta.persistence.*;
import lombok.*;
import pawtropolis.model.dto.DTOClass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bag")
public class BagDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @Column(name="inventory_id")
    private InventoryDTO inventoryDTO;
    private int volume = 20;
    private int occupiedSlots=0;

    public BagDTO(InventoryDTO inventoryDTO){
        this.inventoryDTO = inventoryDTO;
    }
}
