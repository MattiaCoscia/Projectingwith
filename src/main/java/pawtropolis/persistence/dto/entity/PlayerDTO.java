package pawtropolis.persistence.dto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pawtropolis.persistence.dto.items.BagDTO;
import pawtropolis.persistence.dto.items.InventoryDTO;

@Getter
@Setter
@Entity
@Table(name = "player")
public class PlayerDTO extends EntityDTO{

	@OneToOne
	private BagDTO bagDTO;
	public PlayerDTO() {
		this.bagDTO = new BagDTO(new InventoryDTO());
	}
}