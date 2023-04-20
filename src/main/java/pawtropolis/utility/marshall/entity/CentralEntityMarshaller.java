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
    Map<BusinessAndDTOBranchClassKey,EntityBranchMarshaller> marshallersMap;
    @Autowired
    public CentralEntityMarshaller(ApplicationContext applicationContext){
        marshallersMap = new HashMap<>();
        applicationContext.getBeansOfType(EntityBranchMarshaller.class).forEach((key, value)->{
            marshallersMap.put(value.getDoubleKey(),value);
        });
    }
    public <A extends DTOClasses, B extends ConcrateClasses> B marshaller(A animalDTO){
        if(!ObjectUtils.isEmpty(animalDTO)){
            EntityBranchMarshaller marshaller = marshallersMap.get(animalDTO.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && animalDTO.getClass().isAssignableFrom(marshaller.getDTOClass())){
                return (B) marshaller.marshall(animalDTO);
            }
        }
        return null;
    }
    public <A extends DTOClasses, B extends ConcrateClasses> A marshaller(B animal){
        if(!ObjectUtils.isEmpty(animal)){
            EntityBranchMarshaller marshaller = marshallersMap.get(animal.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && animal.getClass().isAssignableFrom(marshaller.getBusinessClass())){
                return (A) marshaller.marshall(animal);
            }
        }
        return null;
    }
}
