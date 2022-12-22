package lezione1_dav.model;

import java.time.LocalDate;

public abstract class AnimalWithTail extends Animal {

    private double tailLenght;


    public double getTailLenght() {
        return tailLenght;
    }

    public void setTailLenght(double tailLenght) {
        this.tailLenght = tailLenght;
    }


    protected AnimalWithTail(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height, double tailLenght) {
        super(name, preferedFood, age, dateEntry, weight, height);
        this.tailLenght = tailLenght;
    }
}
