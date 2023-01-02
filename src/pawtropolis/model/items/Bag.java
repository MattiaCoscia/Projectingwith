package pawtropolis.model.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bag {

    private static final Bag bag=new Bag();
    private Map<String, List<Item>> items=new HashMap<>();
    private int volume;

    private int occupiedSlots=0;

    private Bag(){};

    public static Bag getInstance(int volume){
        bag.volume=volume;
        return bag;
    }

    public Map<String, List<Item>> getItems() {
        return items;
    }

    public int getMaxSlots() {
        return volume;
    }

    public void setItems(Map<String, List<Item>> items) {
        this.items = items;
    }

    public void setMaxSlots(int maxSlots) {
        this.volume = maxSlots;
    }

    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    public void setOccupiedSlots(int occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }
}
