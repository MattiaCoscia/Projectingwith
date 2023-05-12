package pawtropolis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.persistence.dto.entity.EntityDTO;

public interface EntityRepository extends JpaRepository<EntityDTO,Long> {
}
