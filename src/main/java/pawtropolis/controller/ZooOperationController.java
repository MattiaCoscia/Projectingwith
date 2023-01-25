package pawtropolis.controller;

import java.util.*;

import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.model.entity.npc.animal.category.AnimalWithTail;
import pawtropolis.model.entity.npc.animal.category.AnimalWithWings;

public class ZooOperationController {

    Map<Class<? extends Animal>, List<Animal>> animals;
    public ZooOperationController() {
        this.animals=new HashMap<>();
    }
    public void addAnimal(Animal animal){
        animals.computeIfAbsent(animal.getClass(),k-> new ArrayList<Animal>()).add(animal);
    }

    public Animal getTallest(List<Animal> animals){
        Optional<Animal> optAnimal=animals.stream().max(Comparator.comparing(Animal::getHeight));
        return optAnimal.isPresent() ? optAnimal.get() : null;
    }
    public Animal getShortest(List<Animal> animals){
        Optional<Animal> optAnimal=animals.stream().min(Comparator.comparing(Animal::getHeight));
        return optAnimal.isPresent() ? optAnimal.get() : null;
    }

    public Animal getHeaviest(List<Animal> animals){
        Optional<Animal> optAnimal=animals.stream().max(Comparator.comparing(Animal::getWeight));
        return optAnimal.isPresent() ? optAnimal.get() : null;
    }
    public Animal getLightest(List<Animal> animals){
        Optional<Animal> optAnimal=animals.stream().min(Comparator.comparing(Animal::getWeight));
        return optAnimal.isPresent() ? optAnimal.get() : null;
    }

    public AnimalWithTail getLongestTail(List<AnimalWithTail> animals){
        Optional<AnimalWithTail> optAnimal=animals.stream().max(Comparator.comparing(AnimalWithTail::getTailLenght));
        return optAnimal.isPresent()? optAnimal.get() : null;
    }

    public AnimalWithWings getWidestWingSpan(List<AnimalWithWings> animals){
        Optional<AnimalWithWings> optAnimal=animals.stream().max(Comparator.comparing(AnimalWithWings::getWingsSpan));
        return optAnimal.isPresent()? optAnimal.get() : null;
    }
}
