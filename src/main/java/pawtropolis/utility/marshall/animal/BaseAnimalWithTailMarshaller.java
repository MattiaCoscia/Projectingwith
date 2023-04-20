package pawtropolis.utility.marshall.animal;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithTail;
import pawtropolis.model.entity.npc.animal.category.AnimalWithTail;

public abstract class BaseAnimalWithTailMarshaller<A extends AnimalDTOWithTail, B extends AnimalWithTail> extends BaseAnimalMarshaller<A,B> {
    protected B marshallFromDTO(A animalDTO, Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animallClass)){
            B animal = super.marshallFromDTO(animalDTO,animallClass);
            animal.setTailLenght(animalDTO.getTailLenght());
            return animal;
        }
        return null;
    }

    protected A marshallToDTO(B animal, Class<A> animallClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animallClass)){
            A animalDTO = super.marshallToDTO(animal,animallClass);
            animalDTO.setTailLenght(animal.getTailLenght());
            return animalDTO;
        }
        return null;
    }
}
