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
@Table(name = "item_stored")
public class ItemStoredDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int quantity;

    @Column(name = "personal_name")
    private String personalName;

    @ManyToOne
    @JoinColumn(name = "blueprint_id", referencedColumnName = "id")
    private ItemBlueprintDTO itemBlueprintDTO;

    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private InventoryDTO inventoryDTO;
}
