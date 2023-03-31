package pawtropolis.model.entity.npc.animal.category;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class AnimalWithTail extends Animal {
    private double tailLenght;

}
