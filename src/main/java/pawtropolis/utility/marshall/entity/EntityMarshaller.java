package pawtropolis.utility.marshall.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.EntityDTO;
import pawtropolis.model.entity.Entity;
import pawtropolis.utility.marshall.ConcrateMarshaller;

import java.util.HashMap;
import java.util.Map;

@Component
public class EntityMarshaller implements ConcrateMarshaller<EntityDTO, Entity> {
    Map<BusinessAndDTOBranchClassKey<EntityDTO, Entity>, BranchEntityMarshaller<EntityDTO, Entity>> marshallersMap;
    @Autowired
    public EntityMarshaller(ApplicationContext applicationContext){
        marshallersMap = new HashMap<>();
        applicationContext.getBeansOfType(BranchEntityMarshaller.class).forEach((key, value)->{
            marshallersMap.put(value.getDoubleKey(),value);
        });
    }
    @Override
    public Entity marshall(pawtropolis.model.dto.entity.EntityDTO dtoInstance) {
        if(!ObjectUtils.isEmpty(dtoInstance)){
            BranchEntityMarshaller<EntityDTO, Entity> marshaller = marshallersMap.get(dtoInstance.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && dtoInstance.getClass().isAssignableFrom(marshaller.getDTOClass())){
                return marshaller.marshall(dtoInstance);
            }
        }
        return null;
    }

    @Override
    public EntityDTO marshall(Entity businessInstance) {
        if(!ObjectUtils.isEmpty(businessInstance)){
            BranchEntityMarshaller<EntityDTO, Entity> marshaller = marshallersMap.get(businessInstance.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && businessInstance.getClass().isAssignableFrom(marshaller.getBusinessClass())){
                return marshaller.marshall(businessInstance);
            }
        }
        return null;
    }
}
