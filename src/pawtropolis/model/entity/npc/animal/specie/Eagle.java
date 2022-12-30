package pawtropolis.model.entity.npc.animal.specie;

import java.time.LocalDate;

import pawtropolis.model.entity.npc.animal.category.AnimalWithWings;

public class Eagle extends AnimalWithWings {

        public Eagle(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height,
        		double wingsSpan,double lifePoints, int positionX, int positionY) {
                super(name, preferedFood, age, dateEntry, weight, height, wingsSpan,lifePoints,positionX,positionY);
        }
}