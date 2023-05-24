package pawtropolis.utility.marshall.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.map.Door;
import pawtropolis.persistence.dto.map.DoorDTO;
import pawtropolis.persistence.dto.map.GameMapDTO;
import pawtropolis.persistence.dto.map.RoomDTO;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomNameKeyGenerator;
import pawtropolis.utility.marshall.ConcrateMarshaller;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameMapMarshaller implements ConcrateMarshaller<GameMapDTO,GameMap> {
    private RoomMarshaller roomMarshaller;
    private DoorMarhsaller doorMarhsaller;
    @Autowired
    public GameMapMarshaller(RoomMarshaller roomMarshaller, DoorMarhsaller doorMarhsaller){
        this.roomMarshaller = roomMarshaller;
        this.doorMarhsaller = doorMarhsaller;
    }
    @Override
    public GameMap marshall(GameMapDTO mapDTO){
        if(!ObjectUtils.isEmpty(mapDTO)){
            GameMap map = new GameMap();
            map.setHeightMap(mapDTO.getHeightMap());
            map.setWidthMap(mapDTO.getWidthMap());
            map.setId(map.getId());
            if(!ObjectUtils.isEmpty(mapDTO.getRooms())){
                Map<String, Room> rooms = new HashMap<>();
                map.setRooms(rooms);
                mapDTO.getRooms().forEach((key,roomDTO) -> {
                    Room roomBusiness = roomMarshaller.marshall(roomDTO);
                    rooms.put(key,roomBusiness);
                });
                mapDTO.getRooms().forEach((key,roomDTO)->{
                    Room roomBusiness = map.getRooms().get(key);
                    roomDTO.getAdiacentDoors().forEach((dirKey,doorDTO)->{
                        if(roomBusiness.getAdiacentDoors().get(dirKey) == null){
                            RoomDTO oppositeRoomDTO = doorDTO.getRoomA() != roomDTO ? doorDTO.getRoomA() : doorDTO.getRoomB();
                            Room roomBusinessAdiacent = map.getRooms()
                                    .get(RoomNameKeyGenerator.giveKeyForRoom(oppositeRoomDTO.getPositionY(),oppositeRoomDTO.getPositionX()));

                            Door businessDoor = doorMarhsaller.marshall(doorDTO);
                            businessDoor.setRoomA(roomBusiness);
                            businessDoor.setRoomB(roomBusinessAdiacent);
                            roomBusiness.getAdiacentDoors().put(dirKey,businessDoor);
                            roomBusinessAdiacent.getAdiacentDoors().put(DirectionEnum.oppositeValue(dirKey),businessDoor);
                        }
                    });
                });
            }
            return map;
        }
        return null;
    }
    @Override
    public GameMapDTO marshall(GameMap map){
        if(!ObjectUtils.isEmpty(map)){
            GameMapDTO mapDTO = new GameMapDTO();
            mapDTO.setHeightMap(map.getHeightMap());
            mapDTO.setWidthMap(map.getWidthMap());
            mapDTO.setId(mapDTO.getId());
            if(!ObjectUtils.isEmpty(map.getRooms())){
                Map<String, RoomDTO> rooms = new HashMap<>();
                mapDTO.setRooms(rooms);
                map.getRooms().forEach((key,room) -> {
                    RoomDTO roomBusiness = roomMarshaller.marshall(room);
                    rooms.put(key,roomBusiness);
                });
                map.getRooms().forEach((key,businessRoom)->{
                    RoomDTO dtoRoom = mapDTO.getRooms().get(key);
                    businessRoom.getAdiacentDoors().forEach((dirKey,doorBusiness)->{
                        if(dtoRoom.getAdiacentDoors().get(dirKey) == null){
                            Room oppositeRoomBusiness = doorBusiness.getRoomA() != businessRoom ? doorBusiness.getRoomA() : doorBusiness.getRoomB();
                            RoomDTO roomDTOAdiacent = mapDTO.getRooms()
                                    .get(RoomNameKeyGenerator.giveKeyForRoom(oppositeRoomBusiness.getPositionY(),oppositeRoomBusiness.getPositionX()));

                            DoorDTO dtoDoor = doorMarhsaller.marshall(doorBusiness);
                            dtoDoor.setRoomA(dtoRoom);
                            dtoDoor.setRoomB(roomDTOAdiacent);
                            dtoRoom.getAdiacentDoors().put(dirKey,dtoDoor);
                            roomDTOAdiacent.getAdiacentDoors().put(DirectionEnum.oppositeValue(dirKey),dtoDoor);
                        }
                    });
                });
            }
            return mapDTO;
        }
        return null;
    }
}
