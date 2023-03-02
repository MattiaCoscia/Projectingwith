package pawtropolis.utility.strategy;

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
public class DropStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Autowired
    private GameMap map;
    @Override
    public ActionEnum execute(String object) {
        if(!ObjectUtils.isEmpty(object)){
        Room actualRoom = map.getRooms()[player.getPositionY()][player.getPositionX()];
        List<Item> itemsOftype = player.getBag().getItems().get(object);
        Item item = itemsOftype != null ? player.getBag().getItems().get(object).get(0) : null;
        if (item != null) {
            actualRoom.getItems().computeIfAbsent(object, k -> new ArrayList<>()).add(item);
            player.getBag().getItems().get(object).remove(0);
            System.out.println(item.getName() + " has been dropped in the room");
            if (itemsOftype.isEmpty()) {
                player.getBag().getItems().remove(object, itemsOftype);
            }
            player.getBag().setOccupiedSlots(player.getBag().getOccupiedSlots() - item.getVolume());
        }
        return ActionEnum.DROP;

        }
        return ActionEnum.UNKNOWN_COMMAND;
    }

    public DropStrategy(){

    }

    public DropStrategy(Player player,GameMap gameMap){
        this.player = player;
        this.map = gameMap;
    }
}
