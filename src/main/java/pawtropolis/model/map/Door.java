package pawtropolis.model.map;

import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.BusinessClass;
import pawtropolis.model.items.ItemStored;

import java.util.EnumMap;

@Getter
@Setter
public class Door extends BusinessClass {
    private EnumMap<DirectionEnum,Room> rooms;
    private boolean isOpen;
    private ItemStored keyItem;

    public Door() {
        this.rooms = new EnumMap<>(DirectionEnum.class);
    }

    public boolean changeState(ItemStored keyItem){
        if(this.keyItem.getHashKey() == keyItem.getHashKey()){
            isOpen = true;
        }
        return isOpen;
    }
}
