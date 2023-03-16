package pawtropolis.utility.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Item;
import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.util.ArrayList;
import java.util.List;
@Component
@Slf4j
public class GetStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;
    @Override
    public ActionEnum execute(String object) {
       if(!ObjectUtils.isEmpty(object)){
        Room actualRoom = map.getRooms().get(map.giveKeyForRoom(player.getPositionY(),player.getPositionX()));
        List<Item> itemsOftype = actualRoom.getItems().get(object);
        Item item = itemsOftype != null ? actualRoom.getItems().get(object).get(0) : null;
        if (item != null) {
            if (item.getVolume() <= player.getBag().getVolume() - player.getBag().getOccupiedSlots()) {
                player.getBag().getItems().computeIfAbsent(object, k -> new ArrayList<>()).add(item);
                actualRoom.getItems().get(object).remove(0);
                player.getBag().setOccupiedSlots(player.getBag().getOccupiedSlots() + item.getVolume());
                log.info(item.getName() + " has been put in the bag");
                if (itemsOftype.isEmpty()) {
                    actualRoom.getItems().remove(object, itemsOftype);
                }
            } else {
                log.info("the bag is full");
            }
        }
        return ActionEnum.GET;
       }
       return ActionEnum.UNKNOWN_COMMAND;
    }
}
