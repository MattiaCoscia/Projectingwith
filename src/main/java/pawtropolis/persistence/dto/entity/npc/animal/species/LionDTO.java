package pawtropolis.persistence.dto.entity.npc.animal.species;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.persistence.dto.entity.npc.animal.category.AnimalDTOWithTail;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class LionDTO extends AnimalDTOWithTail{

}