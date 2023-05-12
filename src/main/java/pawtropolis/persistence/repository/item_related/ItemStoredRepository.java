package pawtropolis.persistence.repository.item_related;

import org.springframework.data.jpa.repository.JpaRepository;
import pawtropolis.persistence.dto.items.ItemStoredDTO;

public interface ItemStoredRepository extends JpaRepository<ItemStoredDTO,Integer> {
}
