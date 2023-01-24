package pawtropolis.model.entity.npc.animal.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AnimalWithWings extends Animal {
    private double wingsSpan;


}
