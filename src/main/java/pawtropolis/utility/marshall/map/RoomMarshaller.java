package pawtropolis.utility.marshall.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.map.RoomDTO;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.map.Room;
import pawtropolis.utility.marshall.entity.CentralEntityMarshaller;
import pawtropolis.utility.marshall.item_related.InventoryMarshaller;

import java.util.ArrayList;
import java.util.List;

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
                List<Entity> entityList = new ArrayList<>();
                roomDTO.getNpcs().forEach(npc ->{
                    Entity entity = centralEntityMarshaller.marshaller(npc);
                    entityList.add(entity);
                });
            }
            room.setChainPosition(roomDTO.getChainPosition());
            room.setPositionX(roomDTO.getPositionX());
            room.setPositionY(roomDTO.getPositionY());
            room.setType(roomDTO.getType());
            return room;
        }
        return null;
    }

    public RoomDTO marshallToDTO(Room room){
        if(!ObjectUtils.isEmpty(room)){
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setName(room.getName());
            roomDTO.setId(room.getId());
            roomDTO.setInventoryDTO(inventoryMarshaller.marshallToDTO(room.getInventory()));
            if(!ObjectUtils.isEmpty(room.getNpcs())){
                List<Entity> entityList = new ArrayList<>();
                room.getNpcs().forEach(npc ->{
                    Entity entity = centralEntityMarshaller.marshaller(npc);
                    entityList.add(entity);
                });
            }
            roomDTO.setChainPosition(room.getChainPosition());
            roomDTO.setPositionX(room.getPositionX());
            roomDTO.setPositionY(room.getPositionY());
            roomDTO.setType(room.getType());
            return roomDTO;
        }
        return null;
    }
}
