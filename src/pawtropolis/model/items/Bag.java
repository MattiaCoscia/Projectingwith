package pawtropolis.model.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bag {

    private static final Bag bag=new Bag();
    private Map<String, List<Item>> items=new HashMap<>();
    private int volume;

    private int occupiedSlots=0;


    public static Bag getInstance(int volume){
        bag.volume=volume;
        return bag;
    }


    public int getMaxSlots() {
        return volume;
    }

}
