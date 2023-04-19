package pawtropolis.model.dto.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "item_stored")
public class ItemStoredDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "blueprint_id")
    private ItemBlueprintDTO itemBlueprintDTO;
    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "inventory_id")
    private InventoryDTO inventoryDTO;
    public ItemStoredDTO(ItemBlueprintDTO itemBlueprintDTO, int quantity){
        this.itemBlueprintDTO = itemBlueprintDTO;
        this.quantity = quantity;
    }
}
