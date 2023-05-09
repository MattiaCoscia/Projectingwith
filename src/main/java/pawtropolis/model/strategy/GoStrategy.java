package pawtropolis.model.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.controller.InputManager;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.DirectionEnum;
import pawtropolis.model.map.Door;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomNameKeyGenerator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
@Slf4j
public class GoStrategy extends Strategy{
    private Player player;
    private GameMap map;
    private ActionEnum action = ActionEnum.GO;
    @Autowired
    private InputManager inputManager;
    @Autowired
    public GoStrategy(Player player, GameMap gameMap, InputManager inputManager){
        super(ActionEnum.GO);
        this.player = player;
        this.map = gameMap;
        this.inputManager = inputManager;
    }
    @Override
    public ActionEnum execute(List<String> parameters) {
        if(!ObjectUtils.isEmpty(parameters)) {
            for (int i = 0; i < StrategyMapping.NUMBER_OF_PARAMETER_PER_STRATEGY.get(ActionEnum.GO); i++) {
                String direction = parameters.get(i);
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
            }
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
            String yesOrNo = inputManager.chooseFromOptions("the door is locked, do you want to open it?",List.of("Y","N"));
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
