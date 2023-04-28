package pawtropolis.utility.marshall.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.map.GameMapDTO;
import pawtropolis.model.dto.map.RoomDTO;
import pawtropolis.model.dto.map.RoomNameKeyGenerator;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameMapMarshaller {
    private RoomMarshaller roomMarshaller;
    @Autowired
    public GameMapMarshaller(RoomMarshaller roomMarshaller){
        this.roomMarshaller = roomMarshaller;
    }
    public GameMap marshallFromDTO(GameMapDTO mapDTO){
        if(!ObjectUtils.isEmpty(mapDTO)){
            GameMap map = new GameMap();
            map.setHeightMap(mapDTO.getHeightMap());
            map.setWidthMap(mapDTO.getWidthMap());
            map.setId(map.getId());
            if(!ObjectUtils.isEmpty(mapDTO.getRooms())){
                Map<String, Room> rooms = new HashMap<>();
                map.setRooms(rooms);
                mapDTO.getRooms().forEach((key,roomDTO) -> {
                    Room roomBusiness = roomMarshaller.marshallFromDTO(roomDTO);
                    rooms.put(key,roomBusiness);
                });
                mapDTO.getRooms().forEach((key,roomDTO)->{
                    Room roomBusiness = map.getRooms().get(key);
                    roomDTO.getAdiacentRooms().forEach((dirKey,roomDTOAdiancent)->{
                        Room roomBusinessAdiacent = map.getRooms()
                                .get(RoomNameKeyGenerator.giveKeyForRoom(roomDTOAdiancent.getPositionY(),roomDTOAdiancent.getPositionX()));
                        roomBusiness.setSingleRoom(dirKey, roomBusinessAdiacent);
                        roomBusinessAdiacent.setSingleRoom(DirectionEnum.oppositeValue(dirKey),roomBusiness);
                    });
                });
            }
            return map;
        }
        return null;
    }
    public GameMapDTO marshallToDTO(GameMap map){
        if(!ObjectUtils.isEmpty(map)){
            GameMapDTO mapDTO = new GameMapDTO();
            mapDTO.setHeightMap(map.getHeightMap());
            mapDTO.setWidthMap(map.getWidthMap());
            mapDTO.setId(mapDTO.getId());
            if(!ObjectUtils.isEmpty(map.getRooms())){
                Map<String, RoomDTO> rooms = new HashMap<>();
                mapDTO.setRooms(rooms);
                map.getRooms().forEach((key,room) -> {
                    RoomDTO roomBusiness = roomMarshaller.marshallToDTO(room);
                    rooms.put(key,roomBusiness);
                });
                map.getRooms().forEach((key,room)->{
                    RoomDTO roomDTO = mapDTO.getRooms().get(key);
                    room.getAdiacentRooms().forEach((dirKey,roomBusinessAdiancent)->{
                        RoomDTO roomBusinessAdiacent = mapDTO.getRooms()
                                .get(RoomNameKeyGenerator.giveKeyForRoom(roomBusinessAdiancent.getPositionY(),roomBusinessAdiancent.getPositionX()));
                        roomDTO.setSingleRoom(dirKey, roomBusinessAdiacent);
                        roomBusinessAdiacent.setSingleRoom(DirectionEnum.oppositeValue(dirKey),roomDTO);
                    });
                });
            }
            return mapDTO;
        }
        return null;
    }
}
