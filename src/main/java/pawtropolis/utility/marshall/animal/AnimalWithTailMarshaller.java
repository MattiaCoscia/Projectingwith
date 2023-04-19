package pawtropolis.utility.marshall.animal;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithTail;
import pawtropolis.model.entity.npc.animal.category.AnimalWithTail;

public abstract class AnimalWithTailMarshaller extends AnimalMarshaller{
    protected <A extends AnimalDTOWithTail,B extends AnimalWithTail> B marshallFromDTO(A animalDTO, Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animallClass)){
            B animal = baseMarshallFromDTO(animalDTO,animallClass);
            animal.setTailLenght(animalDTO.getTailLenght());
            return animal;
        }
        return null;
    }

    protected <A extends AnimalWithTail,B extends AnimalDTOWithTail> B marshallToDTO(A animal,Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animallClass)){
            B animalDTO = baseMarshallToDTO(animal,animallClass);
            animalDTO.setTailLenght(animal.getTailLenght());
            return animalDTO;
        }
        return null;
    }
}
