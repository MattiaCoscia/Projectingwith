package pawtropolis.model.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.ItemStored;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;
import pawtropolis.utility.RoomNameKeyGenerator;

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
            Room actualRoom = map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(player.getPositionY(), player.getPositionX()));
            ItemStored itemStoredToGet = actualRoom.getItem(object);
            if(itemStoredToGet != null){
                player.addItem(new ItemStored(itemStoredToGet.getItemBlueprint(),1));
                player.getBag().increaseOccupiedSlots(itemStoredToGet.getItemBlueprint().getVolume());
                itemStoredToGet.decreaseQuantity();
                return ActionEnum.GET;
            }
        }
       return ActionEnum.UNKNOWN;
    }
}
