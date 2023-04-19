package pawtropolis.utility.marshall.itemRelated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.items.BagDTO;
import pawtropolis.model.items.Bag;

@Component
public class BagMarshaller {
    private InventoryMarshaller inventoryMarshaller;
    @Autowired
    public BagMarshaller(InventoryMarshaller inventoryMarshaller){
        this.inventoryMarshaller = inventoryMarshaller;
    }
    public Bag marshallFromDTO(BagDTO bagDTO){
        if(!ObjectUtils.isEmpty(bagDTO)){
            Bag bag = new Bag();
            bag.setId(bagDTO.getId());
            bag.setVolume(bagDTO.getVolume());
            bag.setOccupiedSlots(bag.getOccupiedSlots());
            bag.setInventory(inventoryMarshaller.marhsallFromDTO(bagDTO.getInventoryDTO()));
            return bag;
        }
        return null;
    }

    public BagDTO marshallToDTO(Bag bag){
        if(!ObjectUtils.isEmpty(bag)){
            BagDTO bagDTO = new BagDTO();
            bagDTO.setId(bag.getId());
            bagDTO.setVolume(bag.getVolume());
            bagDTO.setOccupiedSlots(bag.getOccupiedSlots());
            bagDTO.setInventoryDTO(inventoryMarshaller.marshallToDTO(bag.getInventory()));
            return bagDTO;
        }
        return null;
    }
}
