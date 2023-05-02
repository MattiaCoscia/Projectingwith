package pawtropolis.utility.marshall.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.map.GameMapDTO;
import pawtropolis.model.dto.map.RoomDTO;
import pawtropolis.utility.RoomNameKeyGenerator;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.marshall.ConcrateMarshaller;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameMapMarshaller implements ConcrateMarshaller<GameMapDTO,GameMap> {
    private RoomMarshaller roomMarshaller;
    @Autowired
    public GameMapMarshaller(RoomMarshaller roomMarshaller){
        this.roomMarshaller = roomMarshaller;
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
