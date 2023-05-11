package pawtropolis.utility.marshall.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.EntityDTO;
import pawtropolis.model.dto.map.RoomDTO;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.map.Room;
import pawtropolis.utility.marshall.ConcrateMarshaller;
import pawtropolis.utility.marshall.entity.EntityMarshaller;
import pawtropolis.utility.marshall.item_related.InventoryMarshaller;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomMarshaller implements ConcrateMarshaller<RoomDTO,Room> {
    private InventoryMarshaller inventoryMarshaller;
    private EntityMarshaller entityMarshaller;
    @Autowired
    public RoomMarshaller(InventoryMarshaller inventoryMarshaller, EntityMarshaller entityMarshaller){
        this.inventoryMarshaller = inventoryMarshaller;
        this.entityMarshaller = entityMarshaller;
    }
    @Override
    public Room marshall(RoomDTO roomDTO){
        if(!ObjectUtils.isEmpty(roomDTO)){
            Room room = new Room();
            room.setName(roomDTO.getName());
            room.setId(roomDTO.getId());
            room.setInventory(inventoryMarshaller.marshall(roomDTO.getInventoryDTO()));
            List<Entity> entityList = new ArrayList<>();
            if(!ObjectUtils.isEmpty(roomDTO.getNpcs())){
                roomDTO.getNpcs().forEach(npc ->{
                    Entity entity = entityMarshaller.marshall(npc);
                    entityList.add(entity);
                });
            }
            room.setNpcs(entityList);
            room.setChainPosition(roomDTO.getChainPosition());
            room.setPositionX(roomDTO.getPositionX());
            room.setPositionY(roomDTO.getPositionY());
            room.setType(roomDTO.getType());
            return room;
        }
        return null;
    }
    @Override
    public RoomDTO marshall(Room room){
        if(!ObjectUtils.isEmpty(room)){
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setName(room.getName());
            roomDTO.setId(room.getId());
            roomDTO.setInventoryDTO(inventoryMarshaller.marshall(room.getInventory()));
            List<EntityDTO> entityList = new ArrayList<>();
            if(!ObjectUtils.isEmpty(room.getNpcs())){
                room.getNpcs().forEach(npc ->{
                    EntityDTO entity = entityMarshaller.marshall(npc);
                    entityList.add(entity);
                });
            }
            roomDTO.setNpcs(entityList);
            roomDTO.setChainPosition(room.getChainPosition());
            roomDTO.setPositionX(room.getPositionX());
            roomDTO.setPositionY(room.getPositionY());
            roomDTO.setType(room.getType());
            return roomDTO;
        }
        return null;
    }
}
