package pawtropolis.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pawtropolis.model.dto.items.ItemBlueprintDTO;
import pawtropolis.model.items.ItemBlueprint;
import pawtropolis.utility.marshall.ConcrateMarshaller;
@Component
public class ItemBlueprintDAO extends AbstractPersistanceClass<ItemBlueprintDTO, ItemBlueprint,Integer> {
    @Autowired
    protected ItemBlueprintDAO(
            JpaRepository<ItemBlueprintDTO, Integer> repository,
            ConcrateMarshaller<ItemBlueprintDTO, ItemBlueprint> marshaller) {
        super(repository, marshaller);
    }

    @Override
    public ItemBlueprint save(ItemBlueprintDTO dto) {
        return null;
    }

    @Override
    public ItemBlueprint edit(ItemBlueprintDTO dto) {
        return null;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public ItemBlueprint get(Integer id) {
        return null;
    }
}
