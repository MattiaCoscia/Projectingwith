package pawtropolis.utility.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.items.Bag;
@Component
public class BagStrategy implements ActionStrategy{
    @Autowired
    private Bag bag;
    @Override
    public ActionEnum execute(String action) {
        System.out.println("In your bag of volume "+bag.getVolume()+" there are:");
        StringBuilder items = new StringBuilder();
        for (String s : bag.getItems().keySet()) {
            items.append(s).append(" x").append(bag.getItems().get(s).size()).append(" | ");
        }
        System.out.println((items.toString()));
        System.out.println("there is "+bag.getOccupiedSlots()+" of volume occupied");
        return ActionEnum.BAG;
    }
}
