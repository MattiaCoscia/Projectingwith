package pawtropolis.persistence.repository.map;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.persistence.dto.map.RoomDTO;

public interface RoomRepository extends JpaRepository<RoomDTO,Integer> {
}
