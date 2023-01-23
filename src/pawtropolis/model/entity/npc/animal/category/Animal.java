package pawtropolis.model.entity.npc.animal.category;

import java.time.LocalDate;

import pawtropolis.model.entity.Entity;

public abstract class Animal extends Entity {
	private String name;
	private String preferedFood;
	private int age;
	private LocalDate dateEntry;
	private double weight;
	private double height;

	protected Animal(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height,
			double lifePoints, int positionX, int positionY) {
		super(name, lifePoints, positionX, positionY);
		this.preferedFood = preferedFood;
		this.age = age;
		this.dateEntry = dateEntry;
		this.weight = weight;
		this.height = height;
	}

	public void setName(String name) {	
		this.name = name;
	}

	public String getName() {
		return name;
	}


	public double getWeight() {
		return weight;
	}

	public double getHeight() {
		return height;
	}
}
