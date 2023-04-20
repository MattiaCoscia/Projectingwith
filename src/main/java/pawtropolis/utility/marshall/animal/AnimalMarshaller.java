package pawtropolis.utility.marshall.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;

import java.util.HashMap;
import java.util.Map;

@Component
public class AnimalMarshaller {
    Map<BusinessAndDTOClassKey,BaseAnimalMarshaller<? extends AnimalDTO, ? extends Animal>> marshallers;

    @Autowired
    public AnimalMarshaller(ApplicationContext applicationContext){
        marshallers = new HashMap<>();
        applicationContext.getBeansOfType(BaseAnimalMarshaller.class).forEach((key,value)->{
            BusinessAndDTOClassKey keyBiClasses = new BusinessAndDTOClassKey(value.getBusinessClass(), value.getDtoClass());
            marshallers.put(keyBiClasses,value);
        });
    }

    public <A extends AnimalDTO, B extends Animal> B marshaller(A animalDTO){
        if(!ObjectUtils.isEmpty(animalDTO)){
            BaseAnimalMarshaller marshaller = marshallers.get(animalDTO.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && marshaller.getDtoClass().equals(animalDTO.getClass())){
                return (B) marshaller.marshall(animalDTO);
            }
        }
        return null;
    }

    public <A extends AnimalDTO, B extends Animal> A marshaller(B animal){
        if(!ObjectUtils.isEmpty(animal)){
            BaseAnimalMarshaller marshaller = marshallers.get(animal.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && marshaller.getBusinessClass().equals(animal.getClass())){
                return (A) marshaller.marshall(animal);
            }
        }
        return null;
    }
}
