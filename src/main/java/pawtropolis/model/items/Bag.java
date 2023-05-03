package pawtropolis.model.items;

import lombok.*;
import pawtropolis.model.BusinessClass;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bag implements BusinessClass {
    private int id;
    private Inventory inventory;
    private int volume = 20;
    private int occupiedSlots=0;

    public Bag(Inventory inventory){
        this.inventory = inventory;
    }
    public void addItem(ItemStored itemStored){
        inventory.addItemTOInventory(itemStored);
    }
    public ItemStored getItem(String item){
        return inventory.getItemFromInventory(item);
    }
    public void  increaseOccupiedSlots(int slots){
        occupiedSlots += slots;
    }
    public void decreaseOccupiedSlots(int slots){
        occupiedSlots -= slots;
    }
}
