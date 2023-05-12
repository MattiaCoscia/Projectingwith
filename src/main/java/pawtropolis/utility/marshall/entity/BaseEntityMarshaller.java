package pawtropolis.utility.marshall.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dto.entity.EntityDTO;
import pawtropolis.model.entity.Entity;
import pawtropolis.utility.marshall.AbstractMarshaller;

@Slf4j
@Getter
public abstract class BaseEntityMarshaller<A extends EntityDTO, B extends Entity> implements AbstractMarshaller<A,B>{

    private final BusinessAndDTOEntityClassKey<A,B> doubleKey;
    protected BaseEntityMarshaller(Class<B> businessClass, Class<A> dtoClass){
        doubleKey = new BusinessAndDTOEntityClassKey<>(businessClass,dtoClass);
    }
    @Override
    public B marshallFromDTO(A entityDTO, Class<B> entityClass) {
        if(!ObjectUtils.isEmpty(entityDTO) && !ObjectUtils.isEmpty(entityClass)){
            B entity = null;
            try {
                entity = entityClass.getConstructor().newInstance();
            } catch (Exception e) {
                String msg = "Si e' verificato un errore nel {} {}";
                log.error(msg,"ISTANZIARE ANIMALE",entityClass.getSimpleName());
                return null;
            }
            entity.setId(entityDTO.getId());
            entity.setLifePoints(entityDTO.getLifePoints());
            entity.setName(entity.getName());
            entity.setPositionX(entity.getPositionX());
            entity.setPositionY(entity.getPositionY());
            return entity;
        }
        return null;
    }
    @Override
    public A marshallToDTO(B entity, Class<A> entityDTOClass){
        if(!ObjectUtils.isEmpty(entity) && !ObjectUtils.isEmpty(entityDTOClass)){
            A entityDTO;
            try {
                entityDTO = entityDTOClass.getConstructor().newInstance();
            } catch (Exception e) {
                String msg = "Si e' verificato un errore nel {} {}";
                log.error(msg,"ISTANZIARE ANIMALE",entityDTOClass.getSimpleName());
                return null;
            }
            entityDTO.setId(entity.getId());
            entityDTO.setLifePoints(entity.getLifePoints());
            entityDTO.setName(entity.getName());
            entityDTO.setPositionX(entity.getPositionX());
            entityDTO.setPositionY(entity.getPositionY());
            return entityDTO;
        }
        return null;
    }
    public Class<A> getDTOClass(){return doubleKey.getDtoClass();}
    public Class<B> getBusinessClass(){
        return doubleKey.getBusinessClass();
    }

    public abstract B marshall(A animalDTO);

    public abstract A marshall(B animal);
}
