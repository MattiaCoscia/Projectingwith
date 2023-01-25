package pawtropolis.model.entity.npc.animal.category;

import java.time.LocalDate;

import lombok.*;
import pawtropolis.model.entity.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Animal extends Entity {
	private String animalName;
	private String preferedFood;
	private int age;
	private LocalDate dateEntry;
	private double weight;
	private double height;

}
