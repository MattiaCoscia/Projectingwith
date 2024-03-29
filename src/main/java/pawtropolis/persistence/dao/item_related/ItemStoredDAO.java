package pawtropolis.persistence.dao.item_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dao.AbstractPersistanceClass;
import pawtropolis.persistence.dto.items.ItemStoredDTO;
import pawtropolis.model.items.ItemStored;
import pawtropolis.utility.marshall.ConcrateMarshaller;

import java.util.Optional;
@Component
public class ItemStoredDAO extends AbstractPersistanceClass<ItemStoredDTO, ItemStored,Integer> {

    @Autowired
    protected ItemStoredDAO(
            JpaRepository<ItemStoredDTO, Integer> repository,
            ConcrateMarshaller<ItemStoredDTO, ItemStored> marshaller) {
        super(repository, marshaller);
    }
    @Override
    public ItemStored save(ItemStoredDTO dto) {
        if(!ObjectUtils.isEmpty(dto)){
            getRepository().save(dto);
            return getMarshaller().marshall(dto);
        }
        return null;
    }

    @Override
    public ItemStored edit(ItemStoredDTO dto) {
        if(!ObjectUtils.isEmpty(dto)){
            Optional<ItemStoredDTO> optDTO = getRepository().findById(dto.getId());
            optDTO.ifPresent(itemDTO -> getRepository().save(itemDTO));
            return getMarshaller().marshall(dto);
        }
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        if(!ObjectUtils.isEmpty(id)){
            Optional<ItemStoredDTO> optDTO = getRepository().findById(id);
            optDTO.ifPresent(itemDTO -> getRepository().delete(itemDTO));
            return optDTO.isPresent();
        }
        return false;
    }

    @Override
    public ItemStored get(Integer id) {
        if(!ObjectUtils.isEmpty(id)){
            Optional<ItemStoredDTO> optDTO = getRepository().findById(id);
            if(optDTO.isPresent()){
                return getMarshaller().marshall(optDTO.get());
            }
        }
        return null;
    }
}
