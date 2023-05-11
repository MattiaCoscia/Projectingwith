package pawtropolis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.model.dto.items.ItemBlueprintDTO;

public interface ItemBlueprintRepository extends JpaRepository<ItemBlueprintDTO, Integer> {
}
