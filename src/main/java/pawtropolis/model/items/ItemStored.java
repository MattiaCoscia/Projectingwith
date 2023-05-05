package pawtropolis.model.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.BusinessClass;

@Getter
@Setter
@AllArgsConstructor
public class ItemStored extends BusinessClass {
    private int id;
    private int quantity;
    private ItemBlueprint itemBlueprint;
    private Inventory inventory;
    public ItemStored(ItemBlueprint itemBlueprint,int quantity){
        this.itemBlueprint = itemBlueprint;
        this.quantity = quantity;
    }
    public ItemStored() {}
    public void increaseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        this.quantity--;
    }
}
