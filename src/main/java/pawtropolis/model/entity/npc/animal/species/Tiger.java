package pawtropolis.model.entity.npc.animal.species;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import pawtropolis.model.entity.npc.animal.category.AnimalWithTail;

@Data
@AllArgsConstructor
@Entity
public class Tiger extends AnimalWithTail {

}