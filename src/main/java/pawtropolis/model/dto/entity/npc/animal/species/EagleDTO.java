package pawtropolis.model.dto.entity.npc.animal.species;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.model.dto.DTOClasses;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithWings;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class EagleDTO extends AnimalDTOWithWings{


}