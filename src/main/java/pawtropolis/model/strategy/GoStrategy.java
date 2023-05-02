package pawtropolis.model.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomNameKeyGenerator;

@Component
@Slf4j
public class GoStrategy implements ActionStrategy {
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;

    @Override
    public ActionEnum execute(String direction) {
        if (!ObjectUtils.isEmpty(direction)) {
            switch (DirectionEnum.fromValue(direction)) {
                case EAST: {
                    if (isRoomConnected(player, 1, 0, map,DirectionEnum.EAST)) {
                        player.setPositionX(player.getPositionX() + 1);
                    }
                    break;
                }
                case WEST: {
                    if (isRoomConnected(player, -1, 0, map,DirectionEnum.WEST)) {
                        player.setPositionX(player.getPositionX() - 1);
                    }
                    break;
                }
                case NORTH: {
                    if (isRoomConnected(player, 0, -1, map,DirectionEnum.NORTH)) {
                        player.setPositionY(player.getPositionY() - 1);
                    }
                    break;
                }
                case SOUTH: {
                    if (isRoomConnected(player, 0, 1, map,DirectionEnum.SOUTH)) {
                        player.setPositionY(player.getPositionY() + 1);
                    }
                    break;
                }
                default: {
                    log.warn("Unknown direction!");
                }
            }
            return ActionEnum.GO;
        }
        return ActionEnum.UNKNOWN;
    }


    private boolean isRoomConnected(Player player, int changeInX, int changeInY, GameMap map, DirectionEnum directionEnum) {
        Room toGo = map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(player.getPositionY() + changeInY,player.getPositionX() + changeInX));
        Room actualRoom = map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(player.getPositionY(),player.getPositionX()));
        Room possibileDirectionRoom =actualRoom.getAdiacentRooms().get(directionEnum);
        return possibileDirectionRoom != null && possibileDirectionRoom == toGo;
    }
}
