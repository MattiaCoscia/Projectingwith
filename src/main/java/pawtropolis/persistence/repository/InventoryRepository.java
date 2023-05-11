package pawtropolis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.model.dto.items.InventoryDTO;

public interface InventoryRepository extends JpaRepository<InventoryDTO,Long> {
}
