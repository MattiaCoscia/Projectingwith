package pawtropolis.utility.marshall.entity.animal;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTOWithWings;
import pawtropolis.model.entity.npc.animal.category.AnimalWithWings;
import pawtropolis.utility.marshall.AbstractMarshaller;

public abstract class BaseAnimalWithWingsMarshaller<A extends AnimalDTOWithWings, B extends AnimalWithWings>
        extends BaseAnimalMarshaller<A,B> implements AbstractMarshaller<A,B> {
    protected BaseAnimalWithWingsMarshaller(Class<B> businessClass, Class<A> dtoClass) {
        super(businessClass, dtoClass);
    }

    @Override
    public B marshallFromDTO(A animalDTO, Class<B> animalClass){
        if(!ObjectUtils.isEmpty(animalDTO) && !ObjectUtils.isEmpty(animalClass)){
            B animal = super.marshallFromDTO(animalDTO, animalClass);
            animal.setWingsSpan(animal.getWingsSpan());
            return animal;
        }
        return null;
    }

    @Override
    public A marshallToDTO(B animal, Class<A> animalDTOClass){
        if(!ObjectUtils.isEmpty(animal) && !ObjectUtils.isEmpty(animalDTOClass)){
            A animalDTO = super.marshallToDTO(animal, animalDTOClass);
            animalDTO.setWingsSpan(animal.getWingsSpan());
            return animalDTO;
        }
        return null;
    }
}
