package pawtropolis.utility.marshall.entity.animal;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.utility.marshall.AbstractMarshaller;
import pawtropolis.utility.marshall.entity.BaseEntityMarshaller;
import pawtropolis.utility.marshall.entity.BusinessAndDTOEntityClassKey;

@Slf4j
@Getter
public abstract class BaseAnimalMarshaller<A extends AnimalDTO, B extends Animal>
        extends BaseEntityMarshaller<A,B> implements AbstractMarshaller<A,B>{
    protected BaseAnimalMarshaller(Class<B> businessClass,Class<A> dtoClass){
        super(businessClass,dtoClass);
    }
    @Override
    public B marshallFromDTO(A animalDTO, Class<B> animalClass) {
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animalClass)){
            B animal = null;
            try {
                animal = animalClass.getConstructor().newInstance();
            } catch (Exception e) {
                String msg = "Si e' verificato un errore nel {} {}";
                log.error(msg,"ISTANZIARE ANIMALE", animalClass.getSimpleName());
                return null;
            }
            animal.setAge(animalDTO.getAge());
            animal.setHeight(animal.getHeight());
            animal.setDateEntry(animal.getDateEntry());
            animal.setPreferedFood(animal.getPreferedFood());
            animal.setWeight(animal.getWeight());
            return animal;
        }
        return null;
    }
    @Override
    public A marshallToDTO(B animal, Class<A> animalDTOClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animalDTOClass)){
            A animalDTO;
            try {
                animalDTO = animalDTOClass.getConstructor().newInstance();
            } catch (Exception e) {
                String msg = "Si e' verificato un errore nel {} {}";
                log.error(msg,"ISTANZIARE ANIMALE", animalDTOClass.getSimpleName());
                return null;
            }
            animalDTO.setAge(animal.getAge());
            animalDTO.setHeight(animal.getHeight());
            animalDTO.setDateEntry(animal.getDateEntry());
            animalDTO.setPreferedFood(animal.getPreferedFood());
            animalDTO.setWeight(animal.getWeight());
            return animalDTO;
        }
        return null;
    }
    public Class<A> getDTOClass(){return getDoubleKey().getDtoClass();}
    public Class<B> getBusinessClass(){return getDoubleKey().getBusinessClass();}

    public abstract B marshall(A animalDTO);

    public abstract A marshall(B animal);
}
