package pawtropolis.model.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.BusinessClass;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Inventory extends BusinessClass {
    private long id;
    private Map<String, ItemStored> items;
    public Inventory(){
        items=new HashMap<>();
    }
    public void addItemTOInventory(ItemStored itemStored){
        if(itemStored != null){
            ItemStored itemStoredToAdd = items.get(itemStored.getItemBlueprint().getName());
            if (itemStoredToAdd != null) {
                itemStoredToAdd.increaseQuantity();
                itemStoredToAdd.setInventory(this);
            } else {
                this.getItems().put(itemStored.getItemBlueprint().getName(), itemStored);
                itemStored.setInventory(this);
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
