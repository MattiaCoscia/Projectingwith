package pawtropolis.model.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.BusinessClass;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class ItemStored extends BusinessClass {
    private int id;
    private int quantity;
    private String personalName;
    private ItemBlueprint itemBlueprint;
    private int hashKey;
    public ItemStored(ItemBlueprint itemBlueprint,int quantity){
        this.personalName = itemBlueprint.getName();
        this.itemBlueprint = itemBlueprint;
        this.quantity = quantity;
        hashKey = Objects.hash(itemBlueprint.getName(),itemBlueprint.getDescription(),itemBlueprint.getVolume());
    }
    public ItemStored() {}
    public void increaseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        this.quantity--;
    }
}
