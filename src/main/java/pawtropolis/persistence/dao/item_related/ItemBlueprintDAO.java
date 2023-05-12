package pawtropolis.persistence.dao.item_related;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.persistence.dao.AbstractPersistanceClass;
import pawtropolis.persistence.dto.items.ItemBlueprintDTO;
import pawtropolis.model.items.ItemBlueprint;
import pawtropolis.utility.marshall.ConcrateMarshaller;

import java.util.Optional;

@Component
public class ItemBlueprintDAO extends AbstractPersistanceClass<ItemBlueprintDTO, ItemBlueprint, Integer> {
    @Autowired
    protected ItemBlueprintDAO(
            JpaRepository<ItemBlueprintDTO, Integer> repository,
            ConcrateMarshaller<ItemBlueprintDTO, ItemBlueprint> marshaller) {
        super(repository, marshaller);
    }

    @Override
    public ItemBlueprint save(ItemBlueprintDTO dto) {
        if (!ObjectUtils.isEmpty(dto)) {
            getRepository().save(dto);
            return getMarshaller().marshall(dto);
        }
        return null;
    }

    @Override
    public ItemBlueprint edit(ItemBlueprintDTO dto) {
        if (!ObjectUtils.isEmpty(dto)) {
            Optional<ItemBlueprintDTO> optDTO = getRepository().findById(dto.getId());
            optDTO.ifPresent(itemBlueprintDTO -> getRepository().save(itemBlueprintDTO));
            return getMarshaller().marshall(dto);
        }
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        if (!ObjectUtils.isEmpty(id)) {
            Optional<ItemBlueprintDTO> optDTO = getRepository().findById(id);
            optDTO.ifPresent(itemBlueprintDTO -> getRepository().delete(itemBlueprintDTO));
            return optDTO.isPresent();
        }
        return false;
    }

    @Override
    public ItemBlueprint get(Integer id) {
        if (!ObjectUtils.isEmpty(id)) {
            Optional<ItemBlueprintDTO> optDTO = getRepository().findById(id);
            if(optDTO.isPresent()){
                return getMarshaller().marshall(optDTO.get());
            }
        }
        return null;
    }
}
