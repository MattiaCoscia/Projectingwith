package pawtropolis.utility.marshall;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithTail;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithWings;
import pawtropolis.model.entity.npc.animal.category.AnimalWithTail;
import pawtropolis.model.entity.npc.animal.category.AnimalWithWings;

public abstract class AnimalWithTailMarshaller extends AnimalMarshaller{
    public <A extends AnimalDTOWithTail,B extends AnimalWithTail> B marshallFromDTO(A animalDTO, Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animallClass)){
            B animal = baseMarshallFromDTO(animalDTO,animallClass);
            animal.setTailLenght(animalDTO.getTailLenght());
            return animal;
        }
        return null;
    }

    public <A extends AnimalWithTail,B extends AnimalDTOWithTail> B marshallToDTO(A animal,Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animallClass)){
            B animalDTO = baseMarshallToDTO(animal,animallClass);
            animalDTO.setTailLenght(animal.getTailLenght());
            return animalDTO;
        }
        return null;
    }
}
