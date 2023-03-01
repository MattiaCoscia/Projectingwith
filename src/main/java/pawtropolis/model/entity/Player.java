package pawtropolis.model.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.model.items.Bag;

@Data
@NoArgsConstructor
public class Player extends Entity {
	private Bag bag;
	public Player(Bag bag2){
		this.bag = bag2;
	}
}
