package pawtropolis.utility.marshall.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.map.RoomDTO;
import pawtropolis.model.map.Room;
import pawtropolis.utility.marshall.item_related.InventoryMarshaller;

@Component
public class RoomMarshaller {
    private InventoryMarshaller inventoryMarshaller;
    @Autowired
    public RoomMarshaller(InventoryMarshaller inventoryMarshaller){
        this.inventoryMarshaller = inventoryMarshaller;
    }
    public Room marshallFromDTO(RoomDTO roomDTO){
        if(!ObjectUtils.isEmpty(roomDTO)){
            Room room = new Room();
            room.setName(roomDTO.getName());
            room.setId(roomDTO.getId());
            room.setInventory(inventoryMarshaller.marhsallFromDTO(roomDTO.getInventoryDTO()));
        }
        return null;
    }
}
