package pawtropolis.model.dto.entity.npc.animal.category;

import jakarta.persistence.Entity;
import lombok.*;
import pawtropolis.model.dto.entity.EntityDTO;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public abstract class AnimalDTO extends EntityDTO {
	private String animalName;
	private String preferedFood;
	private int age;
	private LocalDate dateEntry;
	private double weight;
	private double height;

}
