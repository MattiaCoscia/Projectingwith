package pawtropolis.utility.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.ItemStored;
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
            ItemStored itemStoredToDrop = player.getItem(object);
            if(itemStoredToDrop != null){
                ItemStored itemStoredInRoom = actualRoom.getItem(object);
                if(itemStoredInRoom != null){
                    itemStoredInRoom.increaseQuantity();
                }else{
                    actualRoom.addItem(itemStoredToDrop);
                }
                itemStoredToDrop.decreaseQuantity();
                player.getBag().decreaseOccupiedSlots(itemStoredToDrop.getVolume());
            }
            return ActionEnum.DROP;
        }
        return ActionEnum.UNKNOWN_COMMAND;
    }
}
