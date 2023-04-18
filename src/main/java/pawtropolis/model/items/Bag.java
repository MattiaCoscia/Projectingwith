package pawtropolis.model.items;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bag {
    private int id;
    private Inventory inventory;
    private int volume = 20;
    private int occupiedSlots=0;

    public Bag(Inventory inventory){
        this.inventory = inventory;
    }
    public void addItem(ItemStored itemStored){
        inventory.addItemFromInventory(itemStored);
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
