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
public class GetStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;
    @Override
    public ActionEnum execute(String object) {
        if (!ObjectUtils.isEmpty(object)) {
            Room actualRoom = map.getRooms().get(map.giveKeyForRoom(player.getPositionY(), player.getPositionX()));
            ItemStored itemStoredToGet = actualRoom.getItems().get(object);
            if(itemStoredToGet != null){
                if(itemStoredToGet.getQuantity() <= 1){
                    actualRoom.getItems().remove(itemStoredToGet.getName(), itemStoredToGet);
                }
                player.addItem(new ItemStored(itemStoredToGet.getName(), itemStoredToGet.getDescription(), itemStoredToGet.getVolume(),1));
                player.getBag().increaseOccupiedSlots(itemStoredToGet.getVolume());
                itemStoredToGet.decreaseQuantity();
                return ActionEnum.GET;
            }
        }
       return ActionEnum.UNKNOWN_COMMAND;
    }
}
