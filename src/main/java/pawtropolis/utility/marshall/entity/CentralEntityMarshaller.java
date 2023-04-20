package pawtropolis.utility.marshall.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.ConcrateClasses;
import pawtropolis.model.dto.DTOClasses;
import pawtropolis.model.dto.entity.npc.animal.category.AnimalDTO;
import pawtropolis.model.entity.npc.animal.category.Animal;

import java.util.HashMap;
import java.util.Map;

@Component
public class CentralEntityMarshaller {
    Map<BusinessAndDTOBranchClassKey,EntityBranchMarshaller> marshallers;
    @Autowired
    public CentralEntityMarshaller(ApplicationContext applicationContext){
        marshallers = new HashMap<>();
        applicationContext.getBeansOfType(EntityBranchMarshaller.class).forEach((key, value)->{
            marshallers.put(value.getDoubleKey(),value);
        });
    }
    public <A extends DTOClasses, B extends ConcrateClasses> B marshaller(A animalDTO){
        if(!ObjectUtils.isEmpty(animalDTO)){
            EntityBranchMarshaller marshaller = marshallers.get(animalDTO.getClass());
            if(!ObjectUtils.isEmpty(marshaller)){
                return (B) marshaller.marshall(animalDTO);
            }
        }
        return null;
    }
    public <A extends AnimalDTO, B extends Animal> A marshaller(B animal){
        if(!ObjectUtils.isEmpty(animal)){
            EntityBranchMarshaller marshaller = marshallers.get(animal.getClass());
            if(!ObjectUtils.isEmpty(marshaller)){
                return (A) marshaller.marshall(animal);
            }
        }
        return null;
    }
}
