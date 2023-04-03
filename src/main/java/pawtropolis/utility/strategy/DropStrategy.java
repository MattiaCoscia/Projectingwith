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
                actualRoom.addItem(new ItemStored(itemStoredToDrop.getName(), itemStoredToDrop.getDescription(), itemStoredToDrop.getVolume(), 1));
                player.getBag().decreaseOccupiedSlots(itemStoredToDrop.getVolume());
                itemStoredToDrop.decreaseQuantity();
                if(itemStoredToDrop.getQuantity() == 0){
                    player.getBag().getInventory().getItems().remove(itemStoredToDrop.getName(),itemStoredToDrop);
                }
                return ActionEnum.DROP;
            }
        }
        return ActionEnum.UNKNOWN_COMMAND;
    }
}
