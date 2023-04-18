package pawtropolis.model.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Inventory {
    private long id;
    private Map<String, ItemStored> items;
    public Inventory(){
        items=new HashMap<>();
    }
    public void addItemFromInventory(ItemStored itemStored){
        if(itemStored != null){
            ItemStored itemStoredToAdd = items.get(itemStored.getItemBlueprint().getName());
            if (itemStoredToAdd != null) {
                itemStoredToAdd.setQuantity(itemStoredToAdd.getQuantity() + 1);
            } else {
                this.getItems().put(itemStored.getItemBlueprint().getName(), itemStored);
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
