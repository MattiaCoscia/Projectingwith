package pawtropolis.utility.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

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
                    if (isRoomConnected(player, 1, 0, map)) {
                        player.setPositionX(player.getPositionX() + 1);
                    }
                    break;
                }
                case WEST: {
                    if (isRoomConnected(player, -1, 0, map)) {
                        player.setPositionX(player.getPositionX() - 1);
                    }
                    break;
                }
                case NORTH: {
                    if (isRoomConnected(player, 0, -1, map)) {
                        player.setPositionY(player.getPositionY() - 1);
                    }
                    break;
                }
                case SOUTH: {
                    if (isRoomConnected(player, 0, 1, map)) {
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
        return ActionEnum.UNKNOWN_COMMAND;
    }


    private boolean isRoomConnected(Player player, int changeInX, int changeInY, GameMap map) {
        Room toGo = map.getRooms().get(map.giveKeyForRoom(player.getPositionY() + changeInY,player.getPositionX() + changeInX));
        Room actualRoom = map.getRooms().get(map.giveKeyForRoom(player.getPositionY(),player.getPositionX()));
        for (Room r : actualRoom.getAdiacentRooms()) {
            if (r != null && r.equals(toGo)) {
                return true;
            }
        }
        return false;
    }
}
