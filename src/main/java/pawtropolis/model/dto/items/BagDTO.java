package pawtropolis.model.dto.items;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BagDTO {
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
