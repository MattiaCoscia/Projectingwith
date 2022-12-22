package pawtropolis.model.entity.animal.specie;

import pawtropolis.model.entity.animal.category.AnimalWithTail;

import java.time.LocalDate;

public class Tiger extends AnimalWithTail {

    public Tiger(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height, double tailLenght) {
        super(name, preferedFood, age, dateEntry, weight, height, tailLenght);
    }
}