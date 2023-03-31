package pawtropolis.model.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @Column(name="inventory_id")
    private Inventory inventory;
    private int volume = 20;
    private int occupiedSlots=0;

    public Bag(Inventory inventory){
        this.inventory = inventory;
    }

    public void addItem(ItemStored itemStored){
        ItemStored itemStoredToAdd = this.inventory.getItems().get(itemStored.getName());
        if (itemStoredToAdd != null) {
            itemStoredToAdd.setQuantity(itemStoredToAdd.getQuantity() + itemStored.getQuantity());
        } else {
            this.inventory.getItems().put(itemStored.getName(), itemStored);
        }
    }
    public ItemStored getItem(String item){
        if(item != null){
            ItemStored itemStoredToGet = inventory.getItems().get(item);
            if(itemStoredToGet != null){
                if(itemStoredToGet.getQuantity() <= 1){
                    inventory.getItems().remove(itemStoredToGet.getName(), itemStoredToGet);
                }
                return new ItemStored(itemStoredToGet.getName(), itemStoredToGet.getDescription(), itemStoredToGet.getVolume(), 1);
            }
        }
        return null;
    }

    public void  increaseOccupiedSlots(int slots){
        occupiedSlots += slots;
    }
    public void decreaseOccupiedSlots(int slots){
        occupiedSlots -= slots;
    }
}
