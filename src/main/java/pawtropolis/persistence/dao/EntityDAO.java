package pawtropolis.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.EntityDTO;
import pawtropolis.model.entity.Entity;
import pawtropolis.utility.marshall.ConcrateMarshaller;

import java.util.Optional;

@Component
public class EntityDAO extends AbstractPersistanceClass<EntityDTO, Entity,Long> {
    @Autowired
    protected EntityDAO(JpaRepository<EntityDTO, Long> repository, ConcrateMarshaller<EntityDTO, Entity> marshaller) {
        super(repository, marshaller);
    }


    @Override
    public Entity save(EntityDTO dto) {
        if(!ObjectUtils.isEmpty(dto)){
            getRepository().save(dto);
            return getMarshaller().marshall(dto);
        }
        return null;
    }

    @Override
    public Entity edit(EntityDTO dto) {
        if(!ObjectUtils.isEmpty(dto)){
            Optional<EntityDTO> optDTO = getRepository().findById(dto.getId());
            optDTO.ifPresent(inventoryDTO -> getRepository().save(dto));
            return getMarshaller().marshall(optDTO.get());
        }
        return null;
    }

    @Override
    public boolean remove(Long id) {
        if(!ObjectUtils.isEmpty(id)){
            Optional<EntityDTO> optDTO = getRepository().findById(id);
            optDTO.ifPresent(inventoryDTO -> getRepository().delete(inventoryDTO));
        }
        return false;
    }

    @Override
    public Entity get(Long id) {
        if(!ObjectUtils.isEmpty(id)){
            Optional<EntityDTO> optDTO = getRepository().findById(id);
            if(optDTO.isPresent()){
                return getMarshaller().marshall(optDTO.get());
            }
        }
        return null;
    }
}
