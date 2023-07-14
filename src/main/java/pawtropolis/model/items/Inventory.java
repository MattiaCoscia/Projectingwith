package pawtropolis.model.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.BusinessClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Inventory extends BusinessClass {
    private int id;
    private Map<String, ItemStored> items;
    public Inventory(){
        items=new HashMap<>();
    }
    public void addItemTOInventory(ItemStored itemStored){
        if(itemStored != null){
            ItemStored itemStoredToAdd = items.get(itemStored.getPersonalName());
            if (itemStoredToAdd != null) {
                itemStoredToAdd.increaseQuantity();
            } else {
                this.getItems().put(itemStored.getPersonalName(), itemStored);
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

    public void removeItem(ItemStored item) {
        items.remove(item.getPersonalName(),item);
    }

    public void removeIfKeyValid(ItemStored keyItem) {
        List<String> keyToRemove = new ArrayList<>();
        items.forEach((keyName,key)->{
            if(keyItem != null && keyItem.getHashKey() == key.getHashKey()){
                keyToRemove.add(keyName);
            }
        });
        keyToRemove.forEach(keyName->items.remove(keyName));
    }
}
