package pawtropolis.model.map;

import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.BusinessClass;
import pawtropolis.model.items.ItemStored;
@Getter
@Setter
public class Door extends BusinessClass {
    private boolean isOpen;
    private ItemStored keyItem;

    public boolean changeState(ItemStored keyItem){
        if(this.keyItem.getHashKey() == keyItem.getHashKey()){
            isOpen = true;
        }
        return isOpen;
    }
}
