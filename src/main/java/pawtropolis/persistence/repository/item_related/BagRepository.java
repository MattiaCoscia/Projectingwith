package pawtropolis.persistence.repository.item_related;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.persistence.dto.items.BagDTO;

public interface BagRepository extends JpaRepository<BagDTO,Integer> {
}
