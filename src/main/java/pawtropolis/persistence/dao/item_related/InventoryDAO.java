package pawtropolis.persistence.dao.item_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dao.AbstractPersistanceClass;
import pawtropolis.persistence.dto.items.InventoryDTO;
import pawtropolis.model.items.Inventory;
import pawtropolis.utility.marshall.ConcrateMarshaller;

import java.util.Optional;

@Component
public class InventoryDAO extends AbstractPersistanceClass<InventoryDTO, Inventory,Integer> {
    @Autowired
    protected InventoryDAO(JpaRepository<InventoryDTO, Integer> repository, ConcrateMarshaller<InventoryDTO, Inventory> marshaller) {
        super(repository, marshaller);
    }

    @Override
    public Inventory save(InventoryDTO dto) {
        if(!ObjectUtils.isEmpty(dto)){
            getRepository().save(dto);
            return getMarshaller().marshall(dto);
        }
        return null;
    }

    @Override
    public Inventory edit(InventoryDTO dto) {
        if(!ObjectUtils.isEmpty(dto)){
            Optional<InventoryDTO> optDTO = getRepository().findById(dto.getId());
            optDTO.ifPresent(inventoryDTO -> getRepository().save(dto));
            return getMarshaller().marshall(optDTO.get());
        }
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        if(!ObjectUtils.isEmpty(id)){
            Optional<InventoryDTO> optDTO = getRepository().findById(id);
            optDTO.ifPresent(inventoryDTO -> getRepository().delete(inventoryDTO));
        }
        return false;
    }

    @Override
    public Inventory get(Integer id) {
        if(!ObjectUtils.isEmpty(id)){
            Optional<InventoryDTO> optDTO = getRepository().findById(id);
            if(optDTO.isPresent()){
                return getMarshaller().marshall(optDTO.get());
            }
        }
        return null;
    }
}
