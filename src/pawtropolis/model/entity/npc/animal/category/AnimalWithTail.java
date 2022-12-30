package pawtropolis.model.entity.npc.animal.category;

import java.time.LocalDate;

public abstract class AnimalWithTail extends Animal {

    private double tailLenght;


    public double getTailLenght() {
        return tailLenght;
    }

    public void setTailLenght(double tailLenght) {
        this.tailLenght = tailLenght;
    }


    protected AnimalWithTail(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height
    		, double tailLenght,double lifePoints, int positionX, int positionY) {
        super(name, preferedFood, age, dateEntry, weight, height,lifePoints,positionX,positionY);
        this.tailLenght = tailLenght;
    }
}
