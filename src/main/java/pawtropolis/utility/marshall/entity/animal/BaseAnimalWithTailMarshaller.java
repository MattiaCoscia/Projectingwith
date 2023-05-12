package pawtropolis.utility.marshall.entity.animal;

import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dto.entity.npc.animal.category.AnimalDTOWithTail;
import pawtropolis.model.entity.npc.animal.category.AnimalWithTail;
import pawtropolis.utility.marshall.AbstractMarshaller;

public abstract class BaseAnimalWithTailMarshaller<A extends AnimalDTOWithTail, B extends AnimalWithTail>
        extends BaseAnimalMarshaller<A,B> implements AbstractMarshaller<A,B> {
    protected BaseAnimalWithTailMarshaller(Class<B> businessClass, Class<A> dtoClass) {
        super(businessClass, dtoClass);
    }

    @Override
    public B marshallFromDTO(A animalDTO, Class<B> animalClass){
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animalClass)){
            B animal = super.marshallFromDTO(animalDTO, animalClass);
            animal.setTailLenght(animalDTO.getTailLenght());
            return animal;
        }
        return null;
    }

    @Override
    public A marshallToDTO(B animal, Class<A> animalDTOClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animalDTOClass)){
            A animalDTO = super.marshallToDTO(animal, animalDTOClass);
            animalDTO.setTailLenght(animal.getTailLenght());
            return animalDTO;
        }
        return null;
    }
}
