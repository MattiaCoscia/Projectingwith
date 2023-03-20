package pawtropolis.utility.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
@Component
@Slf4j
public class DropStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;
    @Override
    public ActionEnum execute(String object) {
        if (!ObjectUtils.isEmpty(object)) {
            Room actualRoom = map.getRooms().get(map.giveKeyForRoom(player.getPositionY(), player.getPositionX()));
            Item itemToDrop = player.removeItem(object);
            if(itemToDrop != null){
                Item itemInRoom = actualRoom.getItems().get(object);
                if(itemInRoom != null){
                    itemInRoom.increaseQuantity();
                }else{
                    actualRoom.getItems().put(itemToDrop.getName(),itemToDrop);
                }
                player.getBag().decreaseOccupiedSlots(itemToDrop.getVolume());
            }
            return ActionEnum.DROP;
        }
        return ActionEnum.UNKNOWN_COMMAND;
    }
}
