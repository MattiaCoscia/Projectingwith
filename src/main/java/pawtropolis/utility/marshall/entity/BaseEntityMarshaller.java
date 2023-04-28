package pawtropolis.utility.marshall.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.EntityDTO;
import pawtropolis.model.entity.Entity;
import pawtropolis.utility.marshall.AbstractMarshaller;

@Slf4j
@Getter
public abstract class BaseEntityMarshaller<A extends EntityDTO, B extends Entity> implements AbstractMarshaller<A,B>{

    private final BusinessAndDTOEntityClassKey<A,B> doubleKey;
    protected BaseEntityMarshaller(Class<B> businessClass, Class<A> dtoClass){
        doubleKey = new BusinessAndDTOEntityClassKey<>(businessClass,dtoClass);
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
            animal.setId(animalDTO.getId());
            animal.setLifePoints(animalDTO.getLifePoints());
            animal.setName(animal.getName());
            animal.setPositionX(animal.getPositionX());
            animal.setPositionY(animal.getPositionY());
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
            animalDTO.setId(animal.getId());
            animalDTO.setLifePoints(animal.getLifePoints());
            animalDTO.setName(animal.getName());
            animalDTO.setPositionX(animal.getPositionX());
            animalDTO.setPositionY(animal.getPositionY());
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
