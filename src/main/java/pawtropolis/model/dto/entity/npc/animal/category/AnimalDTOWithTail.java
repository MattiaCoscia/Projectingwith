package pawtropolis.model.dto.entity.npc.animal.category;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class AnimalDTOWithTail extends AnimalDTO {
    private double tailLenght;

}
