package pawtropolis.utility.marshall.entity.animal;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.utility.marshall.AbstractMarshaller;

@Slf4j
@Getter
public abstract class BaseAnimalMarshaller<A extends AnimalDTO, B extends Animal> implements AbstractMarshaller<A,B>{

    private final BusinessAndDTOAnimalClassKey<A,B> doubleKey;

    protected BaseAnimalMarshaller(Class<B> businessClass,Class<A> dtoClass){
        doubleKey = new BusinessAndDTOAnimalClassKey<A,B>(businessClass,dtoClass);
    }
    @Override
    public B marshallFromDTO(A animalDTO, Class<B> animalClass) {
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animalClass)){
            B animal = null;
            try {
                animal = animalClass.getConstructor().newInstance();
            } catch (Exception e) {
                String msg = "Si e' verificato un errore nel {} {}";
                log.error(msg,"ISTANZIARE ANIMALE",animalClass.getSimpleName());
                return null;
            }
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
            return animal;
        }
        return null;
    }
    @Override
    public A marshallToDTO(B animal, Class<A> animalClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animalClass)){
            A animalDTO;
            try {
                animalDTO = animalClass.getConstructor().newInstance();
            } catch (Exception e) {
                String msg = "Si e' verificato un errore nel {} {}";
                log.error(msg,"ISTANZIARE ANIMALE",animalClass.getSimpleName());
                return null;
            }
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
            return animalDTO;
        }
        return null;
    }
    public Class<A> getDTOClass(){return doubleKey.getDtoClass();}
    public Class<B> getBusinessClass(){
        return doubleKey.getBusinessClass();
    }

    public abstract B marshall(A animalDTO);

    public abstract A marshall(B animal);
}
