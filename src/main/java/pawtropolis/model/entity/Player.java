package pawtropolis.model.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import pawtropolis.model.items.Bag;

@Data
@Component
public class
Player extends Entity {
	private Bag bag;
	public Player(){
		this.bag = new Bag();
	}
}
