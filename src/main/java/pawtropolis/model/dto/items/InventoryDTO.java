package pawtropolis.model.dto.items;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class InventoryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "inventory")
    private Map<String, ItemStoredDTO> items;
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
    public ItemStoredDTO getItemFromInventory(String item){
        if(item != null){
            ItemStoredDTO itemStoredToGet = items.get(item);
            if(itemStoredToGet != null){
                return itemStoredToGet;
            }
        }
        return null;
    }
}
