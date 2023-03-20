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
    private Map<String, Item> items=new HashMap<>();
    private int volume = 20;
    private int occupiedSlots=0;

    public void  increaseOccupiedSlots(int slots){
        occupiedSlots += slots;
    }
    public void decreaseOccupiedSlots(int slots){
        occupiedSlots -= slots;
    }
}
