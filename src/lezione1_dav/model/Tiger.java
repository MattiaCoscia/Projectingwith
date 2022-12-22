package lezione1_dav.model;

import java.time.LocalDate;

public class Tiger extends AnimalWithTail {

    public Tiger(String name, String preferedFood, int age, LocalDate dateEntry, double weight, double height, double tailLenght) {
        super(name, preferedFood, age, dateEntry, weight, height, tailLenght);
    }
}