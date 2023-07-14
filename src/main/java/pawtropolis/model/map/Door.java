package pawtropolis.model.map;

import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.BusinessClass;
import pawtropolis.model.items.Inventory;
import pawtropolis.model.items.ItemStored;
import pawtropolis.persistence.dto.items.InventoryDTO;

import java.util.EnumMap;

@Getter
@Setter
public class Door extends BusinessClass {
    private EnumMap<DirectionEnum,Room> rooms;
    private boolean isOpen;
    private Inventory inventory;

    public Door() {
        this.rooms = new EnumMap<>(DirectionEnum.class);
        this.inventory=new Inventory();
    }

    public boolean changeState(ItemStored keyItem){
        inventory.removeIfKeyValid(keyItem);
        if(inventory.getItems().size() == 0){
            isOpen = true;
        }
        return isOpen;
    }
}
