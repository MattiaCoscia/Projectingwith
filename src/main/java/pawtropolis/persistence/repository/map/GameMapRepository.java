package pawtropolis.persistence.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.persistence.dto.map.GameMapDTO;

public interface GameMapRepository extends JpaRepository<GameMapDTO,Integer> {
}
