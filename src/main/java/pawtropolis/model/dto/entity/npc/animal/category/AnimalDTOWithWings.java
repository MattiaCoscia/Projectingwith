package pawtropolis.model.dto.entity.npc.animal.category;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class AnimalDTOWithWings extends AnimalDTO {
    private double wingsSpan;

}
