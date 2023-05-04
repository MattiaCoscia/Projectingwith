package pawtropolis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.model.dto.items.BagDTO;

public interface BagRepository extends JpaRepository<BagDTO,Integer> {
}
