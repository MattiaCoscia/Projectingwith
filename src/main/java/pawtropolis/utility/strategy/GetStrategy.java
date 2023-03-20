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
        if (!ObjectUtils.isEmpty(object)) {
            Room actualRoom = map.getRooms().get(map.giveKeyForRoom(player.getPositionY(), player.getPositionX()));
            Item itemToGet = actualRoom.getItems().get(object);
            if(itemToGet != null){
                if(itemToGet.getQuantity() <= 1){
                    actualRoom.getItems().remove(itemToGet.getName(),itemToGet);
                }
                player.addItem(new Item(itemToGet.getName(), itemToGet.getDescription(),itemToGet.getVolume(),1));
                player.getBag().increaseOccupiedSlots(itemToGet.getVolume());
                itemToGet.decreaseQuantity();
                return ActionEnum.GET;
            }
        }
       return ActionEnum.UNKNOWN_COMMAND;
    }
}
