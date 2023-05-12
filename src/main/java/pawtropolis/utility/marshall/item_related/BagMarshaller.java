package pawtropolis.utility.marshall.item_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dto.items.BagDTO;
import pawtropolis.model.items.Bag;
import pawtropolis.utility.marshall.ConcrateMarshaller;

@Component
public class BagMarshaller implements ConcrateMarshaller<BagDTO,Bag> {
    private InventoryMarshaller inventoryMarshaller;
    @Autowired
    public BagMarshaller(InventoryMarshaller inventoryMarshaller){
        this.inventoryMarshaller = inventoryMarshaller;
    }
    public Bag marshall(BagDTO bagDTO){
        if(!ObjectUtils.isEmpty(bagDTO)){
            Bag bag = new Bag();
            bag.setId(bagDTO.getId());
            bag.setVolume(bagDTO.getVolume());
            bag.setOccupiedSlots(bag.getOccupiedSlots());
            bag.setInventory(inventoryMarshaller.marshall(bagDTO.getInventory()));
            return bag;
        }
        return null;
    }

    public BagDTO marshall(Bag bag){
        if(!ObjectUtils.isEmpty(bag)){
            BagDTO bagDTO = new BagDTO();
            bagDTO.setId(bag.getId());
            bagDTO.setVolume(bag.getVolume());
            bagDTO.setOccupiedSlots(bag.getOccupiedSlots());
            bagDTO.setInventory(inventoryMarshaller.marshall(bag.getInventory()));
            return bagDTO;
        }
        return null;
    }
}
