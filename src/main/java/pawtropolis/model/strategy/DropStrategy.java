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
public class DropStrategy extends Strategy{
    private Player player;
    private GameMap map;
    @Autowired
    public DropStrategy(Player player, GameMap gameMap){
        super(ActionEnum.DROP);
        this.player = player;
        this.map = gameMap;
    }
    @Override
    public ActionEnum execute(List<String> parameters) {
        if(!ObjectUtils.isEmpty(parameters)){
            for(int i=0; i < StrategyMapping.NUMBER_OF_PARAMETER_PER_STRATEGY.get(ActionEnum.DROP);i++){
                String object = parameters.get(i);
                if (!ObjectUtils.isEmpty(object)) {
                    Room actualRoom = map.getRooms().get(RoomNameKeyGenerator.giveKeyForRoom(player.getPositionY(), player.getPositionX()));
                    ItemStored itemStoredToDrop = player.getItem(object);
                    if(itemStoredToDrop != null){
                        actualRoom.addItem(new ItemStored(itemStoredToDrop.getItemBlueprint(), 1));
                        player.getBag().decreaseOccupiedSlots(itemStoredToDrop.getItemBlueprint().getVolume());
                        itemStoredToDrop.decreaseQuantity();
                        if(itemStoredToDrop.getQuantity() <= 0){
                            player.removeItem(itemStoredToDrop);
                        }
                        return ActionEnum.DROP;
                    }
                }
            }
        }
        return ActionEnum.UNKNOWN;
    }
}
