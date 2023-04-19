package pawtropolis.utility.marshall.animal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;

@Slf4j
public abstract class AnimalMarshaller {
    protected <A extends AnimalDTO, B extends Animal> B baseMarshallFromDTO(A animalDTO, Class<B> animalClass) {
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animalClass)){
            B animal = null;
            try {
                animal = animalClass.getConstructor().newInstance();
                animal.setAge(animalDTO.getAge());
                animal.setId(animalDTO.getId());
                animal.setLifePoints(animalDTO.getLifePoints());
                animal.setName(animal.getName());
                animal.setPositionX(animal.getPositionX());
                animal.setPositionY(animal.getPositionY());
                animal.setHeight(animal.getHeight());
                animal.setDateEntry(animal.getDateEntry());
                animal.setPreferedFood(animal.getPreferedFood());
                animal.setWeight(animal.getWeight());
            } catch (Exception e) {
                String msg = "Si e' verificato un errore nel {} {}";
                log.error(msg,"ISTANZIARE ANIMALE",animalClass.getSimpleName());
            }
            return animal;
        }
        return null;
    }

    protected <A extends Animal, B extends AnimalDTO> B baseMarshallToDTO(A animal, Class<B> animalClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animalClass)){
            B animalDTO = null;
            try {
                animalDTO = animalClass.getConstructor().newInstance();
                animalDTO.setAge(animal.getAge());
                animalDTO.setId(animal.getId());
                animalDTO.setLifePoints(animal.getLifePoints());
                animalDTO.setName(animal.getName());
                animalDTO.setPositionX(animal.getPositionX());
                animalDTO.setPositionY(animal.getPositionY());
                animalDTO.setHeight(animal.getHeight());
                animalDTO.setDateEntry(animal.getDateEntry());
                animalDTO.setPreferedFood(animal.getPreferedFood());
                animalDTO.setWeight(animal.getWeight());
            } catch (Exception e) {
                String msg = "Si e' verificato un errore nel {} {}";
                log.error(msg,"ISTANZIARE ANIMALE",animalClass.getSimpleName());
            }
            return animalDTO;
        }
        return null;
    }
}
