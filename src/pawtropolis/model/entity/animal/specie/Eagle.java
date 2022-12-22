package pawtropolis.model.entity.animal.specie;

import pawtropolis.model.entity.animal.category.AnimalWithWings;

import java.time.LocalDate;

public class Eagle extends AnimalWithWings {

        public Eagle(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height, double wingsSpan) {
                super(name, preferedFood, age, dateEntry, weight, height, wingsSpan);
        }
}