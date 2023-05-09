package pawtropolis.model.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.Door;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomNameKeyGenerator;

import java.util.Map;
import java.util.Scanner;

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
        Room possibileDirectionRoom = actualRoom.getAdiacentRooms().get(directionEnum);
        return possibileDirectionRoom != null && isDoorOpen(player,actualRoom,toGo,directionEnum) && possibileDirectionRoom == toGo;
    }

    private boolean isDoorOpen(Player player,Room actualRoom,Room roomToGo, DirectionEnum directionEnum){
        if(actualRoom.getAdiacentDoors().get(directionEnum).isOpen()){
            return true;
        }else{
            log.info("the door is locked, do you want to open it? y/n");
            Scanner scan = new Scanner(System.in);
            String yesOrNo = scan.nextLine().toLowerCase();
            switch (yesOrNo.toLowerCase()){
                case "y" -> {
                    return chooseItemToOpenEvent(player, actualRoom.getAdiacentDoors().get(directionEnum));
                }
                case "n" -> {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean chooseItemToOpenEvent(Player player, Door door){
        Scanner scan = new Scanner(System.in);
        Map<String, ItemStored> items = player.getBag().getInventory().getItems();
        if(items.size() > 0){
            log.info("choose an item to open the door");
            items.forEach((name,item) -> {
                log.info(name);
            });
            String nameOfChoosenItem = "";
            while (!items.containsKey(nameOfChoosenItem)){
                nameOfChoosenItem = scan.nextLine();
            }
            ItemStored choosenItem = items.get(nameOfChoosenItem);
            if(door.changeState(choosenItem)){
                choosenItem.decreaseQuantity();
                log.info("the door has opened");
            }else{
                log.info("the item choosen was not the right one to open this door");
            }
            return door.isOpen();
        }
        log.info("there are no items in your bag to open the door");
        return false;
    }
}
