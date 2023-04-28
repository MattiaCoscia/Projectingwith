package pawtropolis.utility.marshall.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.BusinessClass;
import pawtropolis.model.dto.DTOClass;

import java.util.HashMap;
import java.util.Map;

@Component
public class CentralEntityMarshaller {
    Map<BusinessAndDTOBranchClassKey<DTOClass, BusinessClass>,EntityBranchMarshaller<DTOClass, BusinessClass>> marshallersMap;
    @Autowired
    public CentralEntityMarshaller(ApplicationContext applicationContext){
        marshallersMap = new HashMap<>();
        applicationContext.getBeansOfType(EntityBranchMarshaller.class).forEach((key, value)->{
            marshallersMap.put(value.getDoubleKey(),value);
        });
    }
    public <A extends DTOClass, B extends BusinessClass> B marshall(A entityDTO){
        if(!ObjectUtils.isEmpty(entityDTO)){
            EntityBranchMarshaller<DTOClass, BusinessClass> marshaller = marshallersMap.get(entityDTO.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && entityDTO.getClass().isAssignableFrom(marshaller.getDTOClass())){
                return (B) marshaller.marshall(entityDTO);
            }
        }
        return null;
    }
    public <A extends DTOClass, B extends BusinessClass> A marshall(B entity){
        if(!ObjectUtils.isEmpty(entity)){
            EntityBranchMarshaller<DTOClass, BusinessClass> marshaller = marshallersMap.get(entity.getClass());
            if(!ObjectUtils.isEmpty(marshaller) && entity.getClass().isAssignableFrom(marshaller.getBusinessClass())){
                return (A) marshaller.marshall(entity);
            }
        }
        return null;
    }
}
