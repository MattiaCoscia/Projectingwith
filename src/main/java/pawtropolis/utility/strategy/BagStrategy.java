package pawtropolis.utility.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.entity.Player;
@Component
@Slf4j
public class BagStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Override
    public ActionEnum execute(String action) {
        log.info("In your bag of volume "+player.getBag().getVolume()+" there are:");
        StringBuilder items = new StringBuilder();
        for (String s : player.getBag().getItems().keySet()) {
            items.append(s).append(" x").append(player.getBag().getItems().get(s).size()).append(" | ");
        }
        log.info((items.toString()));
        log.info("there is "+player.getBag().getOccupiedSlots()+" of volume occupied");
        return ActionEnum.BAG;
    }
}
