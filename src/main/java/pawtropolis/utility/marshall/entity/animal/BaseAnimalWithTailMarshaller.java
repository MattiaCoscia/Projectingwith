package pawtropolis.utility.marshall.entity.animal;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithTail;
import pawtropolis.model.entity.npc.animal.category.AnimalWithTail;
import pawtropolis.utility.marshall.AbstractMarshaller;

public abstract class BaseAnimalWithTailMarshaller<A extends AnimalDTOWithTail, B extends AnimalWithTail>
        extends BaseAnimalMarshaller<A,B> implements AbstractMarshaller<A,B> {
    protected BaseAnimalWithTailMarshaller(Class<B> businessClass, Class<A> dtoClass) {
        super(businessClass, dtoClass);
    }

    @Override
    public B marshallFromDTO(A animalDTO, Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animallClass)){
            B animal = super.marshallFromDTO(animalDTO,animallClass);
            animal.setTailLenght(animalDTO.getTailLenght());
            return animal;
        }
        return null;
    }

    @Override
    public A marshallToDTO(B animal, Class<A> animallClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animallClass)){
            A animalDTO = super.marshallToDTO(animal,animallClass);
            animalDTO.setTailLenght(animal.getTailLenght());
            return animalDTO;
        }
        return null;
    }
}
