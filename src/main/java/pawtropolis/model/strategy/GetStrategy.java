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

import java.util.List;

@Component
@Slf4j
public class GetStrategy extends Strategy{
    private Player player;
    private GameMap map;
    @Autowired
    public GetStrategy(Player player, GameMap gameMap){
        super(ActionEnum.GET);
        this.player = player;
        this.map = gameMap;
    }
    @Override
    public ActionEnum execute(List<String> parameters) {
        if(!ObjectUtils.isEmpty(parameters)) {
            for (int i = 0; i < StrategyMapping.NUMBER_OF_PARAMETER_PER_STRATEGY.get(ActionEnum.GET); i++) {
                String object = parameters.get(i);
                if (!ObjectUtils.isEmpty(object)) {
                    Room actualRoom = map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(player.getPositionY(), player.getPositionX()));
                    ItemStored itemStoredToGet = actualRoom.getItem(object);
                    if (itemStoredToGet != null) {
                        player.addItem(new ItemStored(itemStoredToGet.getItemBlueprint(), 1));
                        player.getBag().increaseOccupiedSlots(itemStoredToGet.getItemBlueprint().getVolume());
                        itemStoredToGet.decreaseQuantity();
                        if(itemStoredToGet.getQuantity() <= 0){
                            actualRoom.removeItem(itemStoredToGet);
                        }
                        return ActionEnum.GET;
                    }
                }
            }
        }
       return ActionEnum.UNKNOWN;
    }
}
