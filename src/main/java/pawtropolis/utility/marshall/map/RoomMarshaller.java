package pawtropolis.utility.marshall.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.map.RoomDTO;
import pawtropolis.model.map.Room;
import pawtropolis.utility.marshall.entity.CentralEntityMarshaller;
import pawtropolis.utility.marshall.item_related.InventoryMarshaller;

@Component
public class RoomMarshaller {
    private InventoryMarshaller inventoryMarshaller;
    private CentralEntityMarshaller centralEntityMarshaller;
    @Autowired
    public RoomMarshaller(InventoryMarshaller inventoryMarshaller, CentralEntityMarshaller centralEntityMarshaller){
        this.inventoryMarshaller = inventoryMarshaller;
        this.centralEntityMarshaller = centralEntityMarshaller;
    }
    public Room marshallFromDTO(RoomDTO roomDTO){
        if(!ObjectUtils.isEmpty(roomDTO)){
            Room room = new Room();
            room.setName(roomDTO.getName());
            room.setId(roomDTO.getId());
            room.setInventory(inventoryMarshaller.marhsallFromDTO(roomDTO.getInventoryDTO()));
            if(!ObjectUtils.isEmpty(roomDTO.getNpcs())){
                roomDTO.getNpcs().forEach(npc ->{
                    centralEntityMarshaller.marshaller(npc);
                });
            }
        }
        return null;
    }
}
