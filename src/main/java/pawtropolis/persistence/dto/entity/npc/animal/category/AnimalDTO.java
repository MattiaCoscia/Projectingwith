package pawtropolis.persistence.dto.entity.npc.animal.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pawtropolis.persistence.dto.entity.EntityDTO;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animal")
public abstract class AnimalDTO extends EntityDTO{
	@Column(name = "animal_name")
	private String animalName;
	@Column(name = "prefered_food")
	private String preferedFood;
	@Column(name = "age")
	private int age;
	@Column(name = "date_entry")
	private String dateEntry;
	@Column(name = "weight")
	private double weight;
	@Column(name = "height")
	private double height;
}
