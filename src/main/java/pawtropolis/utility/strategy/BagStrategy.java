package pawtropolis.utility.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.entity.Player;
import pawtropolis.model.items.Bag;
@Component
public class BagStrategy implements ActionStrategy{
    @Autowired
    private Player player;
    @Override
    public ActionEnum execute(String action) {
        System.out.println("In your bag of volume "+player.getBag().getVolume()+" there are:");
        StringBuilder items = new StringBuilder();
        for (String s : player.getBag().getItems().keySet()) {
            items.append(s).append(" x").append(player.getBag().getItems().get(s).size()).append(" | ");
        }
        System.out.println((items.toString()));
        System.out.println("there is "+player.getBag().getOccupiedSlots()+" of volume occupied");
        return ActionEnum.BAG;
    }
}
