package pawtropolis.model.entity.npc.animal.category;

import java.time.LocalDate;

public abstract class AnimalWithWings extends Animal {
    private double wingsSpan;

    protected AnimalWithWings(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height,
    		double wingsSpan,double lifePoints, int positionX, int positionY) {
        super(name, preferedFood, age, dateEntry, weight, height,lifePoints,positionX,positionY);
        this.wingsSpan = wingsSpan;
    }

    public double getWingsSpan() {
        return wingsSpan;
    }

}
