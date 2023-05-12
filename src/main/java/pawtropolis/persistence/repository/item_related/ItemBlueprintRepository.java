package pawtropolis.persistence.repository.item_related;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.persistence.dto.items.ItemBlueprintDTO;

public interface ItemBlueprintRepository extends JpaRepository<ItemBlueprintDTO, Integer> {
}
