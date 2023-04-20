package pawtropolis.utility.marshall.animal;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithWings;
import pawtropolis.model.entity.npc.animal.category.AnimalWithWings;

public abstract class BaseAnimalWithWingsMarshaller<A extends AnimalDTOWithWings, B extends AnimalWithWings> extends BaseAnimalMarshaller<A,B> {
    protected B marshallFromDTO(A animalDTO, Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animallClass)){
            B animal = super.marshallFromDTO(animalDTO,animallClass);
            animal.setWingsSpan(animal.getWingsSpan());
            return animal;
        }
        return null;
    }

    protected A marshallToDTO(B animal, Class<A> animallClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animallClass)){
            A animalDTO = super.marshallToDTO(animal,animallClass);
            animalDTO.setWingsSpan(animal.getWingsSpan());
            return animalDTO;
        }
        return null;
    }
}
