package pawtropolis.persistence.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.persistence.dto.map.DoorDTO;

public interface DoorRepository extends JpaRepository<DoorDTO,Integer> {
}
