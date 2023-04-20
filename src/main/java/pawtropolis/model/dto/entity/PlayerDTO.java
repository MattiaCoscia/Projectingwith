package pawtropolis.model.dto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pawtropolis.model.dto.DTOClasses;
import pawtropolis.model.dto.items.BagDTO;
import pawtropolis.model.dto.items.InventoryDTO;
import pawtropolis.model.dto.items.ItemStoredDTO;

@Getter
@Setter
@Entity
@Table(name = "player")
public class PlayerDTO extends EntityDTO{
	private BagDTO bagDTO;
	public PlayerDTO() {
		this.bagDTO = new BagDTO(new InventoryDTO());
	}
}
