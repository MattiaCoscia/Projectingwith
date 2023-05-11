package pawtropolis.model.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;

import java.util.List;

@Component
@Slf4j
public class BagStrategy extends Strategy{
    private Player player;
    @Autowired
    public BagStrategy(Player player){
        super(ActionEnum.BAG);
        this.player = player;
    }
    @Override
    public ActionEnum execute(List<String> action) {
        log.info("In your bag of volume "+player.getBag().getVolume()+" there are:");
        StringBuilder items = new StringBuilder();
        for (String s : player.getBag().getInventory().getItems().keySet()) {
            items.append(s).append(" x").append(player.getBag().getInventory().getItems().get(s).getQuantity()).append(" | ");
        }
        log.info((items.toString()));
        log.info("there is "+player.getBag().getOccupiedSlots()+" of volume occupied");
        return ActionEnum.BAG;
    }
}
