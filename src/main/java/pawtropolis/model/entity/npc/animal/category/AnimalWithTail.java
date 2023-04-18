package pawtropolis.model.entity.npc.animal.category;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AnimalWithTail extends Animal {
    private double tailLenght;
}
