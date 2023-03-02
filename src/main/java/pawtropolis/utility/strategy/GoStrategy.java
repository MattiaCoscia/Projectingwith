package pawtropolis.utility.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

@Component
public class GoStrategy implements ActionStrategy {
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;

    @Override
    public ActionEnum execute(String direction) {
        if (!ObjectUtils.isEmpty(direction)) {
            boolean nord = player.getPositionY() - 1 >= 0;
            boolean sud = player.getPositionY() + 1 < map.getRooms().length;
            boolean est = player.getPositionX() + 1 < map.getRooms()[0].length;
            boolean ovest = player.getPositionX() - 1 >= 0;
            switch (DirectionEnum.fromValue(direction)) {
                case EAST :  {
                    if (est && map.getRooms()[player.getPositionY()][player.getPositionX() + 1] != null && isRoomConnected(player, 1, 0, map)) {
                        player.setPositionX(player.getPositionX() + 1);
                    }
                    break;
                }
                case WEST: {
                    if (ovest && map.getRooms()[player.getPositionY()][player.getPositionX() - 1] != null && isRoomConnected(player, -1, 0, map)) {
                        player.setPositionX(player.getPositionX() - 1);
                    }
                    break;
                }
                case NORTH: {
                    if (nord && map.getRooms()[player.getPositionY() - 1][player.getPositionX()] != null && isRoomConnected(player, 0, -1, map)) {
                        player.setPositionY(player.getPositionY() - 1);
                    }
                    break;
                }
                case SOUTH: {
                    if (sud && map.getRooms()[player.getPositionY() + 1][player.getPositionX()] != null && isRoomConnected(player, 0, 1, map)) {
                        player.setPositionY(player.getPositionY() + 1);
                    }
                    break;
                }
                default: {
                    System.out.println("Unknown direction!");
                }
            }
            return ActionEnum.GO;
        }
        return ActionEnum.UNKNOWN_COMMAND;
    }


    private boolean isRoomConnected(Player player, int changeInX, int changeInY, GameMap map) {
        Room toGo = map.getRooms()[player.getPositionY() + changeInY][player.getPositionX() + changeInX];
        Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
        for (Room r : actualRoom.getAdiacentRooms()) {
            if (r != null && r.equals(toGo)) {
                return true;
            }
        }
        return false;
    }

    public GoStrategy(){

    }
    public GoStrategy(Player player,GameMap gameMap){
        this.player = player;
        this.map = gameMap;
    }
}
