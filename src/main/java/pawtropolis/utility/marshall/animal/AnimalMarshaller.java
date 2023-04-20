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
    Map<BusinessAndDTOClassKey,BaseAnimalMarshaller> marshallers;

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
            if(!ObjectUtils.isEmpty(marshaller)){
            }
        }
        return null;
    }
}
