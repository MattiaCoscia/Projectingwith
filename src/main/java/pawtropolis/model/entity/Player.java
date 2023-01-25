package pawtropolis.model.entity;

import lombok.*;
import pawtropolis.model.items.Bag;

@Data
@AllArgsConstructor
public class Player extends Entity {
	private final Bag bag=Bag.getInstance(20);

}
