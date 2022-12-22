package lezione1_dav.controller;

import lezione1_dav.model.*;

import java.util.*;

public class ZooOperationController {

    Map<Class<? extends Animal>, List<Animal>> animals;
    public ZooOperationController() {
        this.animals=new HashMap<>();
    }
    public void addAnimal(Animal animal){
        animals.computeIfAbsent(animal.getClass(),k-> new ArrayList<Animal>()).add(animal);
    }

    public Animal getTallest(List<Animal> animals){
        Optional<Animal> optAnimal=animals.stream().reduce((a,b)->a.getHeight()<b.getHeight()?a:b);
        return optAnimal.isPresent()? optAnimal.get() : null;
    }
}
