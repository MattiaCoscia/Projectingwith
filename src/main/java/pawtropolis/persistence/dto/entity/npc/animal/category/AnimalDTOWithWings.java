package pawtropolis.persistence.dto.entity.npc.animal.category;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class AnimalDTOWithWings extends AnimalDTO {
    private double wingsSpan;

}
