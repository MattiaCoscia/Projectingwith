package pawtropolis.utility.marshall.entity.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;
import pawtropolis.utility.marshall.entity.BranchEntityMarshaller;
import pawtropolis.utility.marshall.entity.BusinessAndDTOEntityClassKey;

import java.util.HashMap;
import java.util.Map;

@Component
public class AnimalMarshaller extends BranchEntityMarshaller<AnimalDTO, Animal> {
    Map<BusinessAndDTOEntityClassKey<AnimalDTO,Animal>,BaseAnimalMarshaller<AnimalDTO,Animal>> marshallersMap;
    @Autowired
    public AnimalMarshaller(ApplicationContext applicationContext){
        super(Animal.class, AnimalDTO.class);
        marshallersMap = new HashMap<>();
        applicationContext.getBeansOfType(BaseAnimalMarshaller.class).forEach((key,value)->{
            marshallersMap.put(value.getDoubleKey(),value);
        });
    }
    @Override
    public <A extends AnimalDTO, B extends Animal> B marshall(A animalDTO){
        if(!ObjectUtils.isEmpty(animalDTO)){
            BaseAnimalMarshaller<AnimalDTO,Animal> marshaller = marshallersMap.get(animalDTO.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && marshaller.getDTOClass().equals(animalDTO.getClass())){
                return (B) marshaller.marshall(animalDTO);
            }
        }
        return null;
    }
    @Override
    public <A extends AnimalDTO, B extends Animal> A marshall(B animal){
        if(!ObjectUtils.isEmpty(animal)){
            BaseAnimalMarshaller<AnimalDTO,Animal> marshaller = marshallersMap.get(animal.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && marshaller.getBusinessClass().equals(animal.getClass())){
                return (A) marshaller.marshall(animal);
            }
        }
        return null;
    }
}
