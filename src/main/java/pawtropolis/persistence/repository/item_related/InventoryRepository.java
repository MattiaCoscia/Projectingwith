package pawtropolis.persistence.repository.item_related;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.persistence.dto.items.InventoryDTO;

public interface InventoryRepository extends JpaRepository<InventoryDTO,Long> {
}
