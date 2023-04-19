package pawtropolis.model.entity.npc.animal.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AnimalWithTail extends Animal {
    private double tailLenght;
}
