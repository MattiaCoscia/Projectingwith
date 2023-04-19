package pawtropolis.utility.marshall.animal;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithWings;
import pawtropolis.model.entity.npc.animal.category.AnimalWithWings;

public abstract class AnimalWithWingsMarshaller extends AnimalMarshaller{
    protected <A extends AnimalDTOWithWings,B extends AnimalWithWings> B marshallFromDTO(A animalDTO,Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animallClass)){
            B animal = baseMarshallFromDTO(animalDTO,animallClass);
            animal.setWingsSpan(animal.getWingsSpan());
            return animal;
        }
        return null;
    }

    protected <A extends AnimalWithWings,B extends AnimalDTOWithWings> B marshallToDTO(A animal,Class<B> animallClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animallClass)){
            B animalDTO = baseMarshallToDTO(animal,animallClass);
            animalDTO.setWingsSpan(animal.getWingsSpan());
            return animalDTO;
        }
        return null;
    }
}