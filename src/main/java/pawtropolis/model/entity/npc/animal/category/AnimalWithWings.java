package pawtropolis.model.entity.npc.animal.category;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AnimalWithWings extends Animal {
    private double wingsSpan;
}
