package pawtropolis.model.entity.npc.animal.species;

import java.time.LocalDate;

import pawtropolis.model.entity.npc.animal.category.AnimalWithTail;

public class Lion extends AnimalWithTail {

	public Lion(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height,
			double tailLenght, double lifePoints, int positionX, int positionY) {
		super(name, preferedFood, age, dateEntry, weight, height, tailLenght, lifePoints, positionX, positionY);
	}
}