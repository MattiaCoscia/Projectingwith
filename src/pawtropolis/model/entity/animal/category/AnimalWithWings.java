package pawtropolis.model.entity.animal.category;

import java.time.LocalDate;

public abstract class AnimalWithWings extends Animal {
    private double wingsSpan;

    protected AnimalWithWings(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height, double wingsSpan) {
        super(name, preferedFood, age, dateEntry, weight, height);
        this.wingsSpan = wingsSpan;
    }

    public double getWingsSpan() {
        return wingsSpan;
    }

    public void setWingsSpan(double wingsSpan) {
        this.wingsSpan = wingsSpan;
    }
}
