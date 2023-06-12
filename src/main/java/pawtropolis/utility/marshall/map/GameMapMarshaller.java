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
                mapDTO.getRooms().forEach((key,mainRoomDTO)->{
                    Room mainRoomBusiness = map.getRooms().get(key);
                    mainRoomDTO.getAdiacentDoors().forEach((dirKey,doorDTO)->{
                        if(mainRoomBusiness.getAdiacentDoors().get(dirKey) == null && doorDTO != null){
                            Door businessDoor = doorMarhsaller.marshall(doorDTO);

                            RoomDTO secondRoomDTO = doorDTO.getRooms().get(dirKey);
                            Room secondRoomBusiness = map.getRooms()
                                    .get(RoomNameKeyGenerator.giveKeyForRoom(secondRoomDTO.getPositionY(),secondRoomDTO.getPositionX()));

                            businessDoor.getRooms().put(dirKey,secondRoomBusiness);
                            businessDoor.getRooms().put(DirectionEnum.oppositeValue(dirKey),mainRoomBusiness);
                            mainRoomBusiness.getAdiacentDoors().put(dirKey,businessDoor);
                            secondRoomBusiness.getAdiacentDoors().put(DirectionEnum.oppositeValue(dirKey),businessDoor);
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
                map.getRooms().forEach((key,mainBusinessRoom)->{
                    RoomDTO mainRoomDTO = mapDTO.getRooms().get(key);
                    mainBusinessRoom.getAdiacentDoors().forEach((dirKey,door)->{
                        if(mainRoomDTO.getAdiacentDoors().get(dirKey) == null && door != null){
                            DoorDTO doorDTO = doorMarhsaller.marshall(door);

                            Room secondRoomBusiness = door.getRooms().get(dirKey);
                            RoomDTO secondRoomDTO = mapDTO.getRooms()
                                    .get(RoomNameKeyGenerator.giveKeyForRoom(secondRoomBusiness.getPositionY(),secondRoomBusiness.getPositionX()));

                            doorDTO.getRooms().put(dirKey,secondRoomDTO);
                            doorDTO.getRooms().put(DirectionEnum.oppositeValue(dirKey),mainRoomDTO);
                            mainRoomDTO.getAdiacentDoors().put(dirKey,doorDTO);
                            secondRoomDTO.getAdiacentDoors().put(DirectionEnum.oppositeValue(dirKey),doorDTO);
                        }
                    });
                });
            }
            return mapDTO;
        }
        return null;
    }
}
