package pawtropolis.model.items;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "inventory")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<String, ItemStored> items;
    public Inventory(){
        items=new HashMap<>();
    }
    public void addItemFromInventory(ItemStored itemStored){
        if(itemStored != null){
            ItemStored itemStoredToAdd = items.get(itemStored.getName());
            if (itemStoredToAdd != null) {
                itemStoredToAdd.setQuantity(itemStoredToAdd.getQuantity() + 1);
            } else {
                this.getItems().put(itemStored.getName(), itemStored);
            }
        }
    }
    public ItemStored getItemFromInventory(String item){
        if(item != null){
            ItemStored itemStoredToGet = items.get(item);
            if(itemStoredToGet != null){
                return itemStoredToGet;
            }
        }
        return null;
    }
}
