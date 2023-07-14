package pawtropolis.persistence.dto.entity.npc.animal.species;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.persistence.dto.entity.npc.animal.category.AnimalDTOWithTail;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "lion")
public class LionDTO extends AnimalDTOWithTail{

}