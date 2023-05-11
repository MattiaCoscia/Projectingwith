package pawtropolis.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.model.dto.items.ItemStoredDTO;

public interface ItemStoredRepository extends JpaRepository<ItemStoredDTO,Integer> {
}
