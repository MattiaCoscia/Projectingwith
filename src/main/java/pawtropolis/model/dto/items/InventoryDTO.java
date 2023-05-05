package pawtropolis.model.dto.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.dto.DTOClass;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class InventoryDTO implements DTOClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private Map<String, ItemStoredDTO> items;
    @OneToOne
    private BagDTO bag;
    public InventoryDTO(){
        items=new HashMap<>();
    }
    public void addItemToInventory(ItemStoredDTO itemStored){
        if(itemStored != null){
            ItemStoredDTO itemStoredToAdd = items.get(itemStored.getItemBlueprintDTO().getName());
            if (itemStoredToAdd != null) {
                itemStoredToAdd.setQuantity(itemStoredToAdd.getQuantity() + 1);
            } else {
                this.getItems().put(itemStored.getItemBlueprintDTO().getName(), itemStored);
            }
        }
    }
}
